package day6

import util.TextScanner

import scala.collection.mutable

object Solution {
    def main(args: Array[String]): Unit = {
        val text = TextScanner.readText("src/day6/input.txt")
        val allAnswers = text.split("\n\n").map(group => group.split('\n'))
        println(part1(allAnswers))
        println(part2(allAnswers))
    }

    def part1(all: Array[Array[String]]): Int = all.map(allAnswers).sum

    def part2(all: Array[Array[String]]): Int = all.map(allAnswersEveryoneHas).sum

    def allAnswers(g: Array[String]): Int = {
        val set: mutable.Set[Char] = new mutable.HashSet
        g.foreach(_.toCharArray.foreach(set.add))
        set.size
    }

    def allAnswersEveryoneHas(g: Array[String]): Int = {
        val map: mutable.Map[Char, Int] = new mutable.HashMap
        g.foreach(_.toCharArray.foreach(c => if (map.contains(c)) map(c) = map(c) + 1 else map(c) = 1))
        map.count(_._2 == g.length)
    }
}
