package athena.kanghyuk.com.core.user.port.out

interface ExistsUserPort {

    fun existByOAuthCode(oauthCode: String): Boolean
}
