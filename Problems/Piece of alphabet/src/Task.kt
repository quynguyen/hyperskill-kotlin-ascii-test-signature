fun main() {
    val alphabet = ('a'..'z').joinToString(separator = "")
    val input = readLine()!!
    println(alphabet.contains(input))
}