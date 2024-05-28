import scala.io.StdIn.readLine

object PongGameObjectOriented {
    val height      = 20
    val width       = 40
    var gameRunning = true

    abstract class GameObject {
        def move(): Unit
        def draw(screen: Array[Array[Char]]): Unit
    }

    case class Ball(var x: Int, var y: Int, var dirX: Int, var dirY: Int) extends GameObject {
        override def move(): Unit = {
            x += dirX
            y += dirY

            // Check for collision with top and bottom walls
            if (y <= 0 || y >= height - 1) dirY = -dirY
        }

        def checkPaddleCollision(leftPaddle: Paddle, rightPaddle: Paddle): Unit = {
            if (x == 1 && y >= leftPaddle.y && y < leftPaddle.y + leftPaddle.height) {
                dirX = -dirX
            } else if (x == width - 2 && y >= rightPaddle.y && y < rightPaddle.y + rightPaddle.height) {
                dirX = -dirX
            }

            // Check for out of bounds (ball goes past paddles)
            if (x <= 0 || x >= width - 1) {
                gameRunning = false
                println("Game Over!")
            }
        }

        override def draw(screen: Array[Array[Char]]): Unit = {
            screen(y)(x) = 'O'
        }
    }

    abstract class Paddle(var y: Int, val height: Int) extends GameObject {
        def moveUp(): Unit = {
            if (y > 0) y -= 1
        }

        def moveDown(): Unit = {
            if (y < height - PongGame.height) y += 1
        }

        override def draw(screen: Array[Array[Char]]): Unit = {
            for (i <- 0 until height) {
                screen(y + i)(if (this.isInstanceOf[LeftPaddle]) 0 else width - 1) = '|'
            }
        }
    }

    case class LeftPaddle(yPos: Int, paddleHeight: Int) extends Paddle(yPos, paddleHeight) {
        override def move(): Unit = {
            // Movement handled externally via moveUp and moveDown
        }
    }

    case class RightPaddle(yPos: Int, paddleHeight: Int) extends Paddle(yPos, paddleHeight) {
        override def move(): Unit = {
            // Movement handled externally via moveUp and moveDown
        }
    }

    val ball = Ball(
      width / 2,
      height / 2,
      if (scala.util.Random.nextBoolean()) 1 else -1,
      if (scala.util.Random.nextBoolean()) 1 else -1
    )
    val leftPaddle  = LeftPaddle(height / 2, 4)
    val rightPaddle = RightPaddle(height / 2, 4)

    def main(args: Array[String]): Unit = {
        while (gameRunning) {
            handleInput()
            ball.move()
            ball.checkPaddleCollision(leftPaddle, rightPaddle)
            drawGame()
            Thread.sleep(100)
        }
    }

    def handleInput(): Unit = {
        if (System.in.available() > 0) {
            val input = readLine()
            input match {
                case "w"        => leftPaddle.moveUp()
                case "s"        => leftPaddle.moveDown()
                case "\u001b[A" => rightPaddle.moveUp()   // Arrow up
                case "\u001b[B" => rightPaddle.moveDown() // Arrow down
                case _          =>                        // Ignore other input
            }
        }
    }

    def drawGame(): Unit = {
        val screen = Array.fill(height, width)(' ')

        // Draw game objects
        ball.draw(screen)
        leftPaddle.draw(screen)
        rightPaddle.draw(screen)

        // Print the screen
        println("\u001b[H") // ANSI escape code to move cursor to top
        screen.foreach { row =>
            println(row.mkString)
        }
    }
}
