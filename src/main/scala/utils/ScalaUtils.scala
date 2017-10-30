package utils

import exceptions.EmptyOptionException

import scala.util.{Failure, Success, Try}

object ScalaUtils
{
  def toTry[A](option: Option[A], exception: => Exception = EmptyOptionException): Try[A] = option match
    {
      case Some(value) => Success(value)
      case None => Failure(exception)
    }
}
