package day2

import util.TextScanner

object Solution {
    def main(args: Array[String]): Unit = {
        val lines = TextScanner.readLines("src/day2/input.txt")
        val policies = parsePolicies(lines)
        println(part1(policies))
        println(part2(policies))
    }

    def part1(policies: Array[PasswordPolicy]): Int = {
        policies.count(_.containsCorrectAmountChars)
    }

    def part2(policies: Array[PasswordPolicy]): Int = {
        policies.count(_.hasExactPositionOnce)
    }

    def parsePolicies(lines: Array[String]): Array[PasswordPolicy] = {
        val policyRegex = """(\d+)-(\d+) (\w): (\w+)""".r
        lines.map {
            case policyRegex(min, max, char, password) =>
                new PasswordPolicy(min.toInt, max.toInt, char.head, password)
        }
    }
}
