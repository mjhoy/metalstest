package com.foo

import cats.Applicative
import cats.Monad
import cats.MonadError
import cats.implicits._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.util.Try

sealed trait MyAppM[+A] {
  def map[B](f: A => B)(implicit ec: ExecutionContext): MyAppM[B]
  def flatMap[B](f: A => MyAppM[B])(implicit ec: ExecutionContext): MyAppM[B]

  def asTry(implicit ec: ExecutionContext): MyAppM[Try[A]]

  def asUnit(implicit ec: ExecutionContext): MyAppM[Unit] = map(_ => ())
}

object MyAppM {
  implicit def myAppMApplicative(implicit
      ec: ExecutionContext
  ): Applicative[MyAppM] =
    new Applicative[MyAppM] {
      override def pure[A](x: A): MyAppM[A] = ???

      override def ap[A, B](ff: MyAppM[A => B])(fa: MyAppM[A]): MyAppM[B] = ???

    }

  implicit def myAppMMonad(implicit ec: ExecutionContext): Monad[MyAppM] =
    new Monad[MyAppM] {
      override def flatMap[A, B](fa: MyAppM[A])(f: A => MyAppM[B]): MyAppM[B] =
        ???

      override def tailRecM[A, B](a: A)(
          f: A => MyAppM[Either[A, B]]
      ): MyAppM[B] =
        ???

      override def pure[A](x: A): MyAppM[A] = ???
    }

  implicit def myAppMMonadError(implicit
      ec: ExecutionContext
  ): MonadError[MyAppM, String] =
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
