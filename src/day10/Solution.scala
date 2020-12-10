package day10

import util.TextScanner

import scala.collection.mutable

object Solution {

    val numbers: Array[Int] = TextScanner.readLinesAsInts("src/day10/input.txt").sorted
    val joltages: Array[Int] = 0 +: numbers :+ numbers.last + 3
    val cache: mutable.HashMap[Int, Long] = new mutable.HashMap[Int, Long]()

    def main(args: Array[String]): Unit = {
        println(part1())
        println(part2())
    }

    def part1(): Int = {
        var previous = joltages(0)
        var count1, count3 = 0
        for (i <- 1 until joltages.length) {
            if (previous == joltages(i) - 1) count1 = count1 + 1
            if (previous == joltages(i) - 3) count3 = count3 + 1
            previous = joltages(i)
        }
        count1 * count3
    }

    def part2(): Long = countPossibilities(0)

    def countPossibilities(index: Int): Long ={
        if (index >= joltages.length - 1)
            return 1
        var sum = 0L
        if (index + 1 <= joltages.length - 1 && joltages(index + 1) - joltages(index) <= 3)
            sum = sum + getCached(index + 1)
        if (index + 2 <= joltages.length - 1 && joltages(index + 2) - joltages(index) <= 3)
            sum = sum + getCached(index + 2)
        if (index + 3 <= joltages.length - 1 && joltages(index + 3) - joltages(index) <= 3)
            sum = sum + getCached(index + 3)
        sum
    }

    def getCached(index: Int): Long = {
        if (!cache.contains(index))
            cache(index) = countPossibilities(index)
        cache(index)

    }
}
