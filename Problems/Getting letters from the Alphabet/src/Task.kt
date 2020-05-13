import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    'a'.until(input.next().first()).forEach { print(it) }
}