package util

object TextScanner {
    def readText(path: String): String = {
        val source = scala.io.Source.fromFile(path)
        try source.mkString finally source.close()
    }

    def readLines(path: String): Array[String] = readText(path).split('\n')

    def readLinesAsInts(path: String): Array[Int] = readLines(path).map(_.toInt)

    def readLinesAsLongs(path: String): Array[Long] = readLines(path).map(_.toLong)

    def readLinesAsCharMap(path: String): Array[Array[Char]] = readLines(path).map(_.toCharArray)
}
