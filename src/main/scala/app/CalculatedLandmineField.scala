package app

case class CalculatedLandmineField(calculatedCells: List[List[CalculatedCell]])

object CalculatedLandmineField
{
  private def getStringForRow(row: List[CalculatedCell]): String = row match {
    case Nil => "\n"
    case x :: xs => getStringForRow(xs) + x.toString
  }

  def getString(calculatedLandmineField: CalculatedLandmineField): String =
    calculatedLandmineField.calculatedCells.foldLeft("") {
      case (string, row) => string + getStringForRow(row)
    }
}
