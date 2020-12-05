package util

object ExtendedLong {
    implicit def richLong(l: Long) = new {
        def isBetween(min: Long, max: Long): Boolean  = l >= min && l <= max
    }
}
