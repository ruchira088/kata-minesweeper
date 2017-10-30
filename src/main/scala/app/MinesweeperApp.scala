package app

import java.nio.file.Paths

import utils.FileUtils

object MinesweeperApp
{
  val INPUT_FILE_PATH = Paths.get("resources/input.txt")

  def main(args: Array[String]): Unit =
  {
    val results = for
      {
        fileContents <- FileUtils.readFile(INPUT_FILE_PATH)
        landmineFields <- FileParser(fileContents)
        calculatedLandmineFields = landmineFields
          .map(LandmineField.calculate)
          .map(rows => CalculatedLandmineField.getString(CalculatedLandmineField(rows)))
          .foldLeft((1, "")) {
            case ((index, output), minefield) => (
              index + 1,
              if (minefield.trim.isEmpty)
                output
              else
                s"""$output
                  |Field #$index:$minefield
                  |""".stripMargin
            )
          }
      }
      yield calculatedLandmineFields._2

    results.fold(
      System.err.println,
      println
    )
  }
}
