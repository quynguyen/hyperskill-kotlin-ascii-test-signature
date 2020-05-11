import java.util.*

fun main() {
    val s = Scanner(System.`in`)
    val (a, b) = Pair(s.nextLine(), s.nextLine())
    print(a.equals(b, ignoreCase = true))
}