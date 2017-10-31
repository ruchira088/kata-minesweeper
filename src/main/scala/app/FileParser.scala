package app

import exceptions.EmptyOptionException
import utils.ScalaUtils

import scala.util.matching.Regex
import scala.util.{Failure, Success, Try}

object FileParser
{
  val DIMENSION_REGEX: Regex = "(\\d+) (\\d+)".r

  def apply(fileContent: List[String]): Try[List[LandmineField]] = {
    for
      {
      dimensions <- ScalaUtils.toTry(fileContent.headOption)

      (rowCount, columnCount) <- dimensions match {
        case DIMENSION_REGEX(rows, columns) => Success((rows.toInt, columns.toInt))
        case _ => ???
      }

      rest <- FileParser(fileContent.tail.drop(rowCount))

      landmineField = LandmineField(columnCount, rowCount, LandmineField.parseRows(fileContent.tail.take(rowCount)))
    }
      yield landmineField :: rest
  }
    .recoverWith {
      case EmptyOptionException => Success(List.empty)
      case exception => Failure(exception)
    }
}
