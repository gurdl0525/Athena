package athena.kanghyuk.com.core.auth.port.out

interface GenerateTokenPort {

    fun generateTokens(subject: String): Pair<String, String>
}
