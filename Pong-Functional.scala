import scala.io.StdIn.readLine

object PongGameFunctional {
    val height = 20
    val width  = 40

    case class Ball(x: Int, y: Int, dirX: Int, dirY: Int)
    case class Paddle(y: Int, height: Int)

    case class GameState(ball: Ball, leftPaddle: Paddle, rightPaddle: Paddle, gameRunning: Boolean)

    def main(args: Array[String]): Unit = {
        val initialState = GameState(
          Ball(
            width / 2,
            height / 2,
            if (scala.util.Random.nextBoolean()) 1 else -1,
            if (scala.util.Random.nextBoolean()) 1 else -1
          ),
          Paddle(height / 2, 4),
          Paddle(height / 2, 4),
          gameRunning = true
        )

        gameLoop(initialState)
    }

    def gameLoop(state: GameState): Unit = {
        if (state.gameRunning) {
            val input      = handleInput(state)
            val movedState = moveBall(input)
            val finalState = checkPaddleCollision(movedState)
            drawGame(finalState)
            Thread.sleep(100)
            gameLoop(finalState)
        } else {
            println("Game Over!")
        }
    }

    def handleInput(state: GameState): GameState = {
        if (System.in.available() > 0) {
            val input = readLine()
            input match {
                case "w" =>
                    val newLeftPaddle =
                        if (state.leftPaddle.y > 0) state.leftPaddle.copy(y = state.leftPaddle.y - 1)
                        else state.leftPaddle
                    state.copy(leftPaddle = newLeftPaddle)
                case "s" =>
                    val newLeftPaddle =
                        if (state.leftPaddle.y < height - state.leftPaddle.height)
                            state.leftPaddle.copy(y = state.leftPaddle.y + 1)
                        else state.leftPaddle
                    state.copy(leftPaddle = newLeftPaddle)
                case "\u001b[A" => // Arrow up
                    val newRightPaddle =
                        if (state.rightPaddle.y > 0) state.rightPaddle.copy(y = state.rightPaddle.y - 1)
                        else state.rightPaddle
                    state.copy(rightPaddle = newRightPaddle)
                case "\u001b[B" => // Arrow down
                    val newRightPaddle =
                        if (state.rightPaddle.y < height - state.rightPaddle.height)
                            state.rightPaddle.copy(y = state.rightPaddle.y + 1)
                        else state.rightPaddle
                    state.copy(rightPaddle = newRightPaddle)
                case _ => state
            }
        } else {
            state
        }
    }

    def moveBall(state: GameState): GameState = {
        val newBallX = state.ball.x + state.ball.dirX
        val newBallY = state.ball.y + state.ball.dirY
        val newDirY  = if (newBallY <= 0 || newBallY >= height - 1) -state.ball.dirY else state.ball.dirY

        state.copy(ball = state.ball.copy(x = newBallX, y = newBallY, dirY = newDirY))
    }

    def checkPaddleCollision(state: GameState): GameState = {
        val newBallDirX =
            if (
              (state.ball.x == 1 && state.ball.y >= state.leftPaddle.y && state.ball.y < state.leftPaddle.y + state.leftPaddle.height) ||
              (state.ball.x == width - 2 && state.ball.y >= state.rightPaddle.y && state.ball.y < state.rightPaddle.y + state.rightPaddle.height)
            ) {
                -state.ball.dirX
            } else {
                state.ball.dirX
            }

        if (state.ball.x <= 0 || state.ball.x >= width - 1) {
            state.copy(gameRunning = false)
        } else {
            state.copy(ball = state.ball.copy(dirX = newBallDirX))
        }
    }

    def drawGame(state: GameState): Unit = {
        val screen = Array.fill(height, width)(' ')

        // Draw ball
        val screenWithBall = screen.updated(state.ball.y, screen(state.ball.y).updated(state.ball.x, 'O'))

        // Draw paddles
        val screenWithLeftPaddle = (0 until state.leftPaddle.height).foldLeft(screenWithBall) { (scr, i) =>
            scr.updated(state.leftPaddle.y + i, scr(state.leftPaddle.y + i).updated(0, '|'))
        }

        val finalScreen = (0 until state.rightPaddle.height).foldLeft(screenWithLeftPaddle) { (scr, i) =>
            scr.updated(state.rightPaddle.y + i, scr(state.rightPaddle.y + i).updated(width - 1, '|'))
        }

        // Print the screen
        println("\u001b[H") // ANSI escape code to move cursor to top
        finalScreen.foreach { row =>
            println(row.mkString)
        }
    }
}
