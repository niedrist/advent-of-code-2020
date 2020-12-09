package day09

import util.TextScanner

object Solution {
    val preambleLength = 25

    def main(args: Array[String]): Unit = {
        val numbers = TextScanner.readLinesAsLongs("src/day09/input.txt")
        println(part1(numbers))
        println(part2(numbers))
    }

    def part1(numbers: Array[Long]): Long = {
        for (i <- preambleLength until numbers.length) {
            if (!doesAddUpAtLeastOnce(i, numbers)) {
                return numbers(i)
            }
        }
        -1
    }

    def part2(numbers: Array[Long]): Long = {
        val toFind = part1(numbers)
        for (i <- numbers.indices) {
            var sum = 0L
            var min = Long.MaxValue
            var max = Long.MinValue
            var j = 0
            while (sum < toFind) {
                val current = numbers(i + j)
                sum = sum + numbers(i + j)
                if (min > current) min = current
                if (max < current) max = current
                j = j + 1
            }
            if (sum == toFind)
                return min + max
        }
        -1
    }

    def doesAddUpAtLeastOnce(pos: Int, numbers: Array[Long]): Boolean = {
        for (i <- pos - preambleLength until pos - 1; j <- pos - preambleLength + 1 until pos) {
            if (numbers(i) + numbers(j) == numbers(pos)) {
                return true
            }
        }
        false
    }
}
