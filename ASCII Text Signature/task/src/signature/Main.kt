package signature

import java.text.DateFormatSymbols
import java.util.*

const val BUFFER = 2

fun main() {
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()
    val symbol = "*"
    val line = symbol.repeat(input.length + BUFFER * 2)
    val (header, footer) = Pair(line, line)
    print(decorate(input, header, footer, symbol))
}

fun decorate(s: String, header: String, footer: String, glyph: String) = """
$header
$glyph $s $glyph
$footer
""".trimIndent()
