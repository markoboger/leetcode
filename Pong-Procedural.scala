import scala.io.StdIn.readLine

object PongGameProcedural {
    val height      = 20
    val width       = 40
    var gameRunning = true

    case class Ball(var x: Int, var y: Int, var dirX: Int, var dirY: Int)
    case class Paddle(var y: Int, height: Int)

    val ball = Ball(
      width / 2,
      height / 2,
      if (scala.util.Random.nextBoolean()) 1 else -1,
      if (scala.util.Random.nextBoolean()) 1 else -1
    )
    val leftPaddle  = Paddle(height / 2, 4)
    val rightPaddle = Paddle(height / 2, 4)

    def main(args: Array[String]): Unit = {
        while (gameRunning) {
            handleInput()
            moveBall()
            drawGame()
            Thread.sleep(100)
        }
    }

    def handleInput(): Unit = {
        if (System.in.available() > 0) {
            val input = readLine()
            input match {
                case "w"        => if (leftPaddle.y > 0) leftPaddle.y -= 1
                case "s"        => if (leftPaddle.y < height - leftPaddle.height) leftPaddle.y += 1
                case "\u001b[A" => if (rightPaddle.y > 0) rightPaddle.y -= 1                           // Arrow up
                case "\u001b[B" => if (rightPaddle.y < height - rightPaddle.height) rightPaddle.y += 1 // Arrow down
                case _ => // Ignore other input
            }
        }
    }

    def moveBall(): Unit = {
        ball.x += ball.dirX
        ball.y += ball.dirY

        // Check for collision with top and bottom walls
        if (ball.y <= 0 || ball.y >= height - 1) ball.dirY = -ball.dirY

        // Check for collision with paddles
        if (ball.x == 1 && ball.y >= leftPaddle.y && ball.y < leftPaddle.y + leftPaddle.height) {
            ball.dirX = -ball.dirX
        } else if (ball.x == width - 2 && ball.y >= rightPaddle.y && ball.y < rightPaddle.y + rightPaddle.height) {
            ball.dirX = -ball.dirX
        }

        // Check for out of bounds (ball goes past paddles)
        if (ball.x <= 0 || ball.x >= width - 1) {
            gameRunning = false
            println("Game Over!")
        }
    }

    def drawGame(): Unit = {
        val screen = Array.fill(height, width)(' ')

        // Draw ball
        screen(ball.y)(ball.x) = 'O'

        // Draw paddles
        for (i <- 0 until leftPaddle.height) {
            screen(leftPaddle.y + i)(0) = '|'
            screen(rightPaddle.y + i)(width - 1) = '|'
        }

        // Print the screen
        println("\u001b[H") // ANSI escape code to move cursor to top
        screen.foreach { row =>
            println(row.mkString)
        }
    }
}
