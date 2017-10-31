package app

case class LandmineField(columnCount: Int, rowCount: Int, grid: Map[(Int, Int), String])

object LandmineField
{
  def parseRows(rows: List[String], rowCount: Int = 0): Map[(Int, Int), String] = rows match
    {
      case x :: xs => {
        val (_, row) = x.trim.split("").foldLeft((0, Map.empty[(Int, Int), String])) {
          case ((column, grid), value) => (column + 1, grid + ((column, rowCount) -> value))
        }

        row ++ parseRows(xs, rowCount + 1)
      }

      case Nil => Map.empty
    }
}
