fun main() {
    val url = readLine()!!
    val (uri, query) = url.split('?')
    var password: String = ""
    for (param in query.split('&').toTypedArray()) {
        val parts = param.split('=').toTypedArray()
        val (key, value) = Pair(parts[0], if (parts[1].isBlank()) "not found" else parts[1])
        if (key == "pass") password = value
        println("$key : $value")
    }
    if (password != "") print("password : $password")
}