package app

case class Coordinate(x: Int, y: Int)

object Coordinate
{
  def getAdjacentCoordinates(coordinate: Coordinate, rowCount: Int, columnCount: Int): List[Coordinate] =
  {
    def getSeries(n: Int)(max: Int): List[Int] = List(n-1, n, n+1).filter(value => value >= 0 && value < max)

    for {
      x <- getSeries(coordinate.x)(columnCount)
      y <- getSeries(coordinate.y)(rowCount) if x != coordinate.x || y != coordinate.y
    } yield Coordinate(x, y)
  }
}
