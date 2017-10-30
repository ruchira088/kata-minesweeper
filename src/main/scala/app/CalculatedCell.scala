package app

case class CalculatedCell(cell: Cell, value: Int)
{
  override def toString = if (cell.hasLandmine) Cell.LANDMINE_SYMBOL else value.toString
}
