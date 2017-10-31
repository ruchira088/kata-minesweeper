package app

import constants.GeneralConstants

case class CalculatedLandmineField(rowCount: Int, columnCount: Int, calculatedGrid: Map[(Int, Int), String])

object CalculatedLandmineField
{
  def getString(calculatedLandmineField: CalculatedLandmineField): String =
    calculatedLandmineField.calculatedGrid
      .groupBy {
        case ((_, y), _) => y
      }
      .toList
      .sortBy {
        case (row, _) => row
      }
      .map {
        case (row, cells) => (row, cells.toList.sortBy { case ((x, _), _) => x })
      }
      .foldLeft("") {
        case (string, (_, values)) => string + values.map(_._2).mkString + "\n"
      }

  def calculate(landmineField: LandmineField): Map[(Int, Int), String] =
    landmineField.grid.map
    {
      case cell @ (_, GeneralConstants.LANDMINE_SYMBOL) => cell

      case cell @ ((x, y), _) => {
        val adjacentCoordinates = Coordinate.getAdjacentCoordinates(
          Coordinate(x, y),
          landmineField.rowCount,
          landmineField.columnCount
        )

        val cellValue = adjacentCoordinates.map {
          case Coordinate(x, y) => landmineField.grid.get((x, y))
            .map(value => if (value == GeneralConstants.LANDMINE_SYMBOL) 1 else 0)
            .getOrElse(0)
        }
          .sum

        cell.copy(_2 = cellValue.toString)
      }

    }
}
