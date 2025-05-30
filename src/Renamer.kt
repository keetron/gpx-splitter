import java.io.File
import java.io.IOException
import java.util.Scanner

fun main() {
    try {
        val sc = Scanner(File("Dolomieten.gpx"))
        while (sc.hasNextLine()) {
            val line = sc.nextLine()
            val fileName = "target/Dolomieten renamed tracks.gpx"

            // rename track
            if (line.contains("<name>")) {
                val trackName = "Do - ${line.substringAfter("<name>").substringBefore("</name>")}"
                File(fileName).appendText("    <name>$trackName</name>\n")
                continue
            }

            // add line to current file
            File(fileName).appendText(line)
            File(fileName).appendText("\n")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
