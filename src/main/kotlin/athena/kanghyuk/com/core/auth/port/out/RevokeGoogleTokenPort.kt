package athena.kanghyuk.com.core.auth.port.out

interface RevokeGoogleTokenPort {

    fun revoke(token: String)
}
