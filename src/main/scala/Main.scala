import cats.Monad
import cats.MonadError
import cats.implicits._

sealed trait MyAppM[+a] {}

object MyAppM {
  implicit def myAppMMonad: Monad[MyAppM] = new Monad[MyAppM] {
    override def flatMap[A, B](fa: MyAppM[A])(f: A => MyAppM[B]): MyAppM[B] =
      ???

    override def tailRecM[A, B](a: A)(f: A => MyAppM[Either[A, B]]): MyAppM[B] =
      ???

    override def pure[A](x: A): MyAppM[A] = ???
  }

  implicit def myAppMMonadError: MonadError[MyAppM, String] =
    new MonadError[MyAppM, String] {

      override def pure[A](x: A): MyAppM[A] = ???

      override def raiseError[A](e: String): MyAppM[A] = ???

      override def handleErrorWith[A](fa: MyAppM[A])(
          f: String => MyAppM[A]
      ): MyAppM[A] = ???

      override def flatMap[A, B](fa: MyAppM[A])(f: A => MyAppM[B]): MyAppM[B] =
        ???

      override def tailRecM[A, B](a: A)(
          f: A => MyAppM[Either[A, B]]
      ): MyAppM[B] = ???

    }
}

object Main extends App {
  println("Hello, World!")
}

object Test {
  def action(a: String): MyAppM[Int] = ???

  def runTraverse(strs: Seq[String]): MyAppM[Seq[Int]] = {
    strs.traverse { str =>
      action(str)
    }
  }
}
