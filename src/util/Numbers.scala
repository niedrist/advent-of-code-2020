package util

object Numbers {
    def isBetween(n: Long, min: Long, max: Long): Boolean = {
        n >= min && n <= max
    }
}
