package athena.kanghyuk.com.core.auth.port.`in`

interface LoginUseCase {

    fun google(token: String): Pair<String, String>

    fun facebook(token: String): Pair<String, String>
}
