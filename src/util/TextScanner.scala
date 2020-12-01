package util

object TextScanner {
    def readLines(path: String): Array[String] = {
        val source = scala.io.Source.fromFile(path)
        try source.mkString.split('\n') finally source.close()
    }

    def readLinesAsInts(path: String): Array[Int] = {
        readLines(path).map(x => x.toInt)
    }
}
