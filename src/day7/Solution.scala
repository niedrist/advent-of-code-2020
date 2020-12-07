package day7

import java.util.Optional

import util.TextScanner

import scala.collection.mutable.HashMap

object Solution {
    val rules = new HashMap[String, Array[Rule]]

    val myBagColor = "shiny gold"

    def main(args: Array[String]): Unit = {
        val lines = TextScanner.readLines("src/day7/input.txt")
        parseRules(lines)
        println(part1(rules))
        println(part2(rules))
    }

    def part1(rules: HashMap[String, Array[Rule]]): Integer = rules.count(rule => hasOuterCase(myBagColor, rule._1, rule._2)) - 1

    def part2(rules: HashMap[String, Array[Rule]]): Integer = countBags(rules(myBagColor)) - 1

    def countBags(containing: Array[Rule]): Integer =
        1 + containing.map(r => r.amount * countBags(rules(r.color))).sum

    def hasOuterCase(toFind: String, bagColor: String, containing: Array[Rule]): Boolean =
        bagColor == toFind || containing.exists(c => hasOuterCase(toFind, c.color, rules(c.color)))

    def parseRule(line: String): (String, Array[Rule]) = {
        val split = line. split(',').map(_.trim.split("\\s+"))
        val bagColor = split(0)(0) + " " + split(0)(1)
        val rules = (parseAmount(split(0).drop(4)) +: split.drop(1).map(parseAmount)).filter(_.isPresent).map(_.get).map(x => Rule(x._2, x._1))
        (bagColor, rules)
    }

    def parseAmount(linePart: Array[String]): Optional[(Int, String)] = {
        if (linePart(0) != "no") {
            return Optional.of(linePart(0).toInt, linePart(1) + " " + linePart(2))
        }
        Optional.empty()
    }

    def parseRules(lines: Array[String]): Unit = {
        lines.foreach(l => {
            val parsed = parseRule(l)
            rules(parsed._1) = parsed._2
        })
    }

    case class Rule(color: String, amount: Integer)
}
