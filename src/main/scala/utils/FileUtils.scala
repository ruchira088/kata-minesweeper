package utils

import java.nio.file.Path

import scala.io.Source
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object FileUtils
{
  def readFile(path: Path): Try[List[String]] = try {
    Success(Source.fromFile(path.toFile).getLines().toList)
  } catch {
    case NonFatal(throwable) => Failure(throwable)
  }
}
