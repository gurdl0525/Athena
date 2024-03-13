package athena.kanghyuk.com.core.auth.port.out

interface DeleteTokenPort {

    fun deleteAccessTokenBySubject(subject: String)

    fun deleteRefreshTokenBySubject(subject: String)
}