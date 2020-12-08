package day08

import util.TextScanner

import scala.collection.mutable

object Solution {
    val regex = "([a-z]+) ([+-][0-9]+)".r

    def main(args: Array[String]): Unit = {
        val instructions = TextScanner.readLines("src/day08/input.txt").map(parseInstruction)
        println(part1(instructions))
        println(part2(instructions))
    }

    def part1(instructions: Array[Instruction]): Int = runProgram(instructions)._1

    def part2(instructions: Array[Instruction]): Int = runFixedProgram(instructions)


    def runFixedProgram(instructions: Array[Solution.Instruction]): Int = {
        for (i <- instructions.indices) {
            if (instructions(i).switchable) {
                instructions(i).switch
                val change = runProgram(instructions)
                if (change._2)
                    return change._1
                instructions(i).switch
            }
        }
        -1
    }

    def runProgram(ins: Array[Solution.Instruction]): (Int, Boolean) = {
        var a, p = 0
        val executed: mutable.HashSet[Int] = mutable.HashSet()
        while (!executed.contains(p) && p < ins.length) {
            executed.add(p)
            val change = ins(p).exec
            a = a + change.aChange
            p = p + change.pChange
        }
        (a, p == ins.length)
    }

    def parseInstruction(line: String): Instruction = line match {
        case regex(c, v) => Instruction(c, v.toInt)
    }

    case class Instruction(var command: String, value: Int) {
        def exec: InstructionChange = {
            command match {
                case "nop" => InstructionChange(0, 1)
                case "jmp" => InstructionChange(0, value)
                case "acc" => InstructionChange(value, 1)
            }
        }

        def switchable: Boolean = command == "nop" || command == "jmp"

        def switch = if (command == "nop") command = "jmp" else if (command == "jmp") command = "nop"
    }

    case class InstructionChange(aChange: Int, pChange: Int)

}
