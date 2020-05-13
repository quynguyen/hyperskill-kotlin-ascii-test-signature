import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val remove = input.next()
    letters@ for (i in 'a'..'z') {
        for (j in remove) {
            if (i == j) continue@letters
        }
        print(i)
    }
}