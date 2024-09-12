import cats.implicits._
import com.foo.F
import com.foo.MyAppM

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object Main extends App {
  println("Hello, World!")
}

object Test {
  def action(t: String, fn: String => MyAppM[String]): MyAppM[String] =
    fn(t)

  def runTraverse(
      strs: Seq[String]
  )(implicit ec: ExecutionContext): MyAppM[Seq[String]] = {
    val z = strs.traverse { str =>
      action(str, s => F.pure(s))
    }

    z
  }
}
