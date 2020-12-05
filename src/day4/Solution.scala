package day4

import util.TextScanner

object Solution {
    def main(args: Array[String]): Unit = {
        val inputText = TextScanner.readText("src/day4/input.txt")
        val passports = parsePassports(inputText)
        println(part1(passports))
        println(part2(passports))
    }

    def part1(passports: Array[Passport]): Long = passports.count(_.isValid)

    def part2(passports: Array[Passport]): Long = passports.count(_.hasValidValues)

    def parsePassports(input: String): Array[Passport] = {
        input.split("\n\n").map(_.split("[\\n\\s]")).map(passportArray => {
            val passport = new Passport()
            passportArray.foreach(keyValuePairString => {
                val keyValuePair = keyValuePairString.split(':')
                passport.addValue(keyValuePair(0), keyValuePair(1))
            })
            passport
        })
    }
}
