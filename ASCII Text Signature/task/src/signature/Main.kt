package signature

import java.util.*
import kotlin.math.max

enum class ASCII(val rows: Array<String>, val width: Int = rows.fold(0) { acc, s -> max(acc, s.length) }) {
    A(arrayOf(
            """____""",
            """|__|""",
            """|  |""")),
    B(arrayOf(
            """___ """,
            """|__]""",
            """|__]""")),
    C(arrayOf(
            """____""",
            """|   """,
            """|___""")),
    D(arrayOf(
            """___ """,
            """|  \""",
            """|__/""")),
    E(arrayOf(
            """____""",
            """|___""",
            """|___""")),
    F(arrayOf(
            """____""",
            """|___""",
            """|   """)),
    G(arrayOf(
            """____""",
            """| __""",
            """|__]""")),
    H(arrayOf(
            """_  _""",
            """|__|""",
            """|  |""")),
    I(arrayOf(
            """_""",
            """|""",
            """|""")),
    J(arrayOf(
            """ _""",
            """ |""",
            """_|""")),
    K(arrayOf(
            """_  _""",
            """|_/ """,
            """| \_""")),
    L(arrayOf(
            """_   """,
            """|   """,
            """|___""")),
    M(arrayOf(
            """_  _""",
            """|\/|""",
            """|  |""")),
    N(arrayOf(
            """_  _""",
            """|\ |""",
            """| \|""")),
    O(arrayOf(
            """____""",
            """|  |""",
            """|__|""")),
    P(arrayOf(
            """___ """,
            """|__]""",
            """|   """)),
    Q(arrayOf(
            """____""",
            """|  |""",
            """|_\|""")),
    R(arrayOf(
            """____""",
            """|__/""",
            """|  \""")),
    S(arrayOf(
            """____""",
            """[__ """,
            """___]""")),
    T(arrayOf(
            """___""",
            """ | """,
            """ | """)),
    U(arrayOf(
            """_  _""",
            """|  |""",
            """|__|""")),
    V(arrayOf(
            """_  _""",
            """|  |""",
            """ \/ """)),
    W(arrayOf(
            """_ _ _""",
            """| | |""",
            """|_|_|""")),
    X(arrayOf(
            """_  _""",
            """ \/ """,
            """_/\_""")),
    Y(arrayOf(
            """_   _""",
            """ \_/ """,
            """  |  """)),
    Z(arrayOf(
            """___ """,
            """  / """,
            """ /__"""));

    companion object {
        const val SPACE_SYMBOL: String = " "
        private const val SPACE_SIZE = 6
        private const val BORDER_SYMBOL: String = "*"
        private const val BORDER_PREFIX: String = "*  "
        private const val BORDER_SUFFIX: String = "  *"
        const val HEIGHT = 3
        val SPACE = SPACE_SYMBOL.repeat(SPACE_SIZE)
        fun symbolLine(length: Int) = BORDER_SYMBOL.repeat(BORDER_PREFIX.length + length + BORDER_SUFFIX.length)
        fun addBorderAndPadding(s: String, l: Int = s.length): String {
            return if (s.length == l) {
                BORDER_PREFIX + s + BORDER_SUFFIX
            } else {
                val difference = l - s.length
                val even = difference % 2 == 0
                val paddingSize = difference / 2
                val leftPadding = SPACE_SYMBOL.repeat(paddingSize)
                val rightPadding = if (even) leftPadding else leftPadding.plus(SPACE_SYMBOL)
                BORDER_PREFIX + leftPadding + s + rightPadding + BORDER_SUFFIX
            }
        }

        fun fromChar(c: Char) = valueOf(c.toString())
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.nextLine().toUpperCase()
    print("Enter person's status: ")
    val status = scanner.nextLine()
    var length = 0
    var symbolLine = ""
    val numRows = ASCII.HEIGHT
    val asciiSpace = ASCII.SPACE
    val words = name.split(" ").toTypedArray()
    for (row in 0 until numRows) {
        val firstRow = row == 0
        val lastRow = row == numRows - 1
        val asciiWords = words.map { word ->
            val asciiLetters = word.map { char -> ASCII.fromChar(char) }
            val asciiWord = asciiLetters.joinToString(separator = ASCII.SPACE_SYMBOL) { it.rows[row] }
            asciiWord
        }
        val asciiRow = asciiWords.joinToString(separator = asciiSpace)

        if (firstRow) {
            length = max(asciiRow.length, status.length)
            symbolLine = ASCII.symbolLine(length)
            println(symbolLine)
        }

        println(ASCII.addBorderAndPadding(asciiRow, length))

        if (lastRow) {
            println(ASCII.addBorderAndPadding(status, length))
            println(symbolLine)
        }
    }
}
