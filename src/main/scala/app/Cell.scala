package app

case class Cell(coordinate: Coordinate, hasLandmine: Boolean)

object Cell
{
  val LANDMINE_SYMBOL = "*"

  def isLandmine(value: String): Boolean = value == LANDMINE_SYMBOL

  def apply(row: Int, column: Int, value: String): Cell =
    Cell(Coordinate(x = column, y = row), isLandmine(value))
}
