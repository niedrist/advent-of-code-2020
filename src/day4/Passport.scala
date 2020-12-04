package day4

import util.Numbers

import scala.collection.mutable

class Passport {
    private val requiredIdentifiers = Array("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    private val fields = new mutable.HashMap[String, String]()

    private val rules: mutable.HashMap[String, String => Boolean] = mutable.HashMap(
        "byr" -> {x => Numbers.isBetween(x.toInt, 1920, 2002)},
        "iyr" -> (x => Numbers.isBetween(x.toInt, 2010, 2020)),
        "eyr" -> (x => Numbers.isBetween(x.toInt, 2020, 2030)),
        "hgt" -> (x => {
            "1([5-8][0-9]|9[0-3])cm".r.pattern.matcher(x).matches() ||
            "(59|6[0-9]|7[0-6])in".r.pattern.matcher(x).matches()
        }),
        "hcl" -> { "#[0-9a-f]{6}".r.pattern.matcher(_).matches() },
        "ecl" -> { Array("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(_) },
        "pid" -> { "[0-9]{9}".r.pattern.matcher(_).matches() },
    )

    def addValue(key: String, value: String) = {
        fields.addOne((key, value))
    }

    def isValid: Boolean = {
        requiredIdentifiers.forall(fields.contains)
    }

    def hasValidValues: Boolean = {
        requiredIdentifiers.forall(field => fields.contains(field) && rules(field)(fields(field)))
    }
}
