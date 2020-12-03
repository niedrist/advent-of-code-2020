package day3

import util.TextScanner

object Solution {
    def main(args: Array[String]): Unit = {
        val map = TextScanner.readLinesAsCharMap("src/day3/input.txt")
        println(part1(map))
        println(part2(map))
    }

    def part1(map: Array[Array[Char]]): Long = {
        val seq = Seq((1, 3))
        calc(map, seq)
    }

    def part2(map: Array[Array[Char]]): Long = {
        val seq = Seq((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))
        calc(map, seq)
    }

    def calc(map: Array[Array[Char]], seq: Seq[(Int, Int)]): Long = {
        seq.map{ case (yIncr: Int, xIncr: Int) =>
            var x, y = 0
            var count: Long = 0
            while (y < map.length) {
                if (map(y)(x) == '#')
                    count = count + 1
                x = (x + xIncr) % map.head.length
                y = y + yIncr
            }
            count
        }.product
    }
}
