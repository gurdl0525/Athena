package athena.kanghyuk.com.core.user.port.out

import athena.kanghyuk.com.application.user.entity.User

interface ReadUserPort {

    fun readByOAuthCode(oauthCode: String): User?
}
