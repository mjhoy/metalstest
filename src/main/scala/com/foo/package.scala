package com

import cats.MonadError
import com.foo.MyAppM

import scala.concurrent.ExecutionContext

package object foo {
  def F(implicit ec: ExecutionContext): MonadError[MyAppM, String] =
    MonadError[MyAppM, String]
}
