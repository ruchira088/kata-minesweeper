package app

import org.scalatest.FlatSpec

class CoordinateSpec extends FlatSpec
{
  "Coordinate.getAdjacentCoordinates" should "" in {
    println(Coordinate.getAdjacentCoordinates(Coordinate(x = 1, y = 1), 2, 2))
  }
}
