import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Args need to be [List of files] [List of <fileNumber>:[Comma seperated page ranges]].")
        exitProcess(1)
    }

    var fileCount = 0
    val file = File(args[0])
    if (!file.exists()) {
        println("File ${args[0]} not found.")
        exitProcess(1)
    }
    val docMap = mutableMapOf<Int, PDDocument>()
    do {
        docMap[fileCount+1] = Loader.loadPDF(File(args[fileCount]))
        fileCount++
    } while (File(args[fileCount]).exists())

    println("Creating pdf file.")
    val output = File(file.absoluteFile.parent + File.separator + file.nameWithoutExtension + "_split.pdf")
    val outputDoc = PDDocument()
    //

    val pageList = mutableListOf<Pair<Int, IntRange>>()
    val valid = Regex("(\\d+):((?:-?\\d+(~-?)?\\d*,)*)(-?\\d+(~-?)?\\d*)") // (\d+):((?:\d+~?\d*,)*)(\d+~?\d*)
    args.slice(fileCount..<args.size).forEach { arg ->
        if (!valid.matches(arg)) {
            println("Argument reading error.")
            exitProcess(1)
        }

        val fileNum = arg.split(':')[0].toInt()
        arg.split(':', ',').drop(1).forEach { sep ->
            var range = sep.split('~').map { it.toInt() }
            // Allow 0 as last page and negative page numbers.
            range = range.map { if (it <= 0) (docMap[fileNum]!!.numberOfPages + it) else it }
            if (range.size == 2) pageList.add(fileNum to range[0]..range[1])
            else pageList.add(fileNum to range[0]..range[0])
        }
    }
    println("Adding pages $pageList.")

    pageList.forEach { pageListItem ->
        for (n in pageListItem.second.first toward pageListItem.second.last) {
            outputDoc.addPage(docMap[pageListItem.first]!!.getPage(n - 1))
        }
    }

    println("Saving output to ${output.path}.")
    outputDoc.save(output)
    outputDoc.close()
    docMap.forEach { it.value.close() }
}

private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}