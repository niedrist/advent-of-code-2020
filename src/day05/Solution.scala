package day05

import util.TextScanner

import scala.collection.SortedSet

object Solution {
    def main(args: Array[String]): Unit = {
        val lines = TextScanner.readLines("src/day05/input.txt")
        val boardingPasses = lines.map(parseBoardingPass)
        println(part1(boardingPasses))
        println(part2(boardingPasses))
    }

    def part1(boardingPasses: Array[BoardingPass]): Int = boardingPasses.maxBy(_.seatId).seatId

    def part2(boardingPasses: Array[BoardingPass]): Int = {
        val seatIds = boardingPasses.map(_.seatId).to(SortedSet)
        (seatIds.min to seatIds.max).find(!seatIds.contains(_)).get
    }

    // Kids, don't try this at home
    def parseBoardingPass(seat: String): BoardingPass = {
        val splitValues = seat.replaceAll("[BR]", "1").replaceAll("[FL]", "0").splitAt(7)
        BoardingPass(Integer.parseInt(splitValues._1, 2), Integer.parseInt(splitValues._2, 2))
    }

    case class BoardingPass(seatRow: Int, seatColumn: Int) {
        def seatId: Int = seatRow * 8 + seatColumn
    }

}
