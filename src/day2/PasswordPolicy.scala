package day2

class PasswordPolicy(var min: Int, var max: Int, var char: Char, var password: String) {
    def containsCorrectAmountChars: Boolean = {
        val count = password.count(_ == char)
        count >= min && count <= max
    }

    def hasExactPositionOnce: Boolean = {
        password(min - 1) == char ^ password(max - 1) == char
    }
}
