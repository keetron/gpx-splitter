import java.io.File
import java.io.IOException
import java.util.Scanner

fun main() {
    try {
        val sc = Scanner(File("Dolomieten.gpx"))
        var fileName = ""
        while (sc.hasNextLine()) {
            val line = sc.nextLine()
            if (line.isNullOrBlank()
                || line.contains("extensions") // irrelevant
                || line.contains("gpx_style") // irrelevant
                || line.contains("</trkpt>") // we close the trkpr later
                || line.contains("<ele>") // irrelevant
                || line.contains("<gpx") // we'll create a new file later
                || line.contains("</gpx") // all files are terminated later
                || line.contains("<trk>") // as gpx tag
                || line.contains("<type>") //irrelevant
                || line.contains("<?xml")
                || line.contains("metadata")
            ) continue


            // if line contains name, set filename
            // start new file
            if (line.contains("<name>")) {
                val trackName = "DO - ${line.substringAfter("<name>").substringBefore("</name>")}"
                fileName = "target/$trackName.gpx"
                File(fileName).createNewFile()
                File(fileName).appendText("<gpx>\n  <trk>\n")
                File(fileName).appendText("    <name>$trackName</name>\n")
                continue
            }

            //if line contains <trkpt, replace the last > with />
            if (line.contains("<trkpt")) {
                if (line.contains("/>")) {
                    File(fileName).appendText(line)
                    File(fileName).appendText("\n")
                    continue
                }
                File(fileName).appendText(line.replace(">", "/>"))
                File(fileName).appendText("\n")
                continue
            }

            // if line contains </trk>, the track ends
            // - append this line with </gpx>
            if (line.contains("</trk>")) {
                File(fileName).appendText(line)
                File(fileName).appendText("\n")
                File(fileName).appendText("</gpx>")
                File(fileName).appendText("\n")
                continue
            }

            // none of the above? add line to current file
            File(fileName).appendText(line)
            File(fileName).appendText("\n")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
