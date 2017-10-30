package app

case class LandmineField(columnCount: Int, rowCount: Int, rows: List[List[Cell]])

object LandmineField
{
  def parseRows(rows: List[String], rowCount: Int = 0): List[List[Cell]] = rows match
    {
      case x :: xs => {
        val (_, cellList) = x.trim.split("").foldLeft((0, List.empty[Cell])) {
          case ((column, cells), value) => (column + 1, Cell(rowCount, column, value) :: cells)
        }

        cellList :: parseRows(xs, rowCount + 1)
      }

      case Nil => List.empty
    }

  def getCell(landmineField: LandmineField)(coordinate: Coordinate): Option[Cell] =
    landmineField.rows.flatten.find(_.coordinate == coordinate)

  def calculate(landmineField: LandmineField): List[List[CalculatedCell]] =
    landmineField.rows.map {
      row => for {
        cell: Cell <- row

        adjacentCoordinates = Coordinate.getAdjacentCoordinates(
          cell.coordinate, landmineField.rowCount, landmineField.columnCount
        )

        getCellFromLandmineField = (getCell _)(landmineField)

        value: Int = adjacentCoordinates
          .map(coordinate => getCellFromLandmineField(coordinate).fold(0)(cell => if (cell.hasLandmine) 1 else 0))
          .sum
      }
        yield CalculatedCell(cell, value)

  }
}
