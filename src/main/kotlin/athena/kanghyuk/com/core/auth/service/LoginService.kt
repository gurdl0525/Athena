package athena.kanghyuk.com.core.auth.service

import athena.kanghyuk.com.common.ReadOnlyUseCase
import athena.kanghyuk.com.core.auth.port.`in`.LoginUseCase
import athena.kanghyuk.com.core.auth.port.out.GenerateTokenPort
import athena.kanghyuk.com.core.auth.port.out.ReadFacebookInfoPort
import athena.kanghyuk.com.core.auth.port.out.ReadGoogleInfoPort
import athena.kanghyuk.com.core.auth.port.out.RevokeGoogleTokenPort
import athena.kanghyuk.com.core.user.port.out.ReadUserPort
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus

@ReadOnlyUseCase
class LoginService(
    private val readGoogleInfoPort: ReadGoogleInfoPort,
    private val revokeGoogleTokenPort: RevokeGoogleTokenPort,
    private val readUserPort: ReadUserPort,
    private val generateTokenPort: GenerateTokenPort,
    private val readFacebookInfoPort: ReadFacebookInfoPort
): LoginUseCase {

    override fun google(token: String): Pair<String, String> {

        val subject = readGoogleInfoPort.infoByToken(token).sub

        val user = readUserPort.readByOAuthCode(subject)
            ?: throw AthenaException(HttpStatus.CONFLICT, "You must be sign-up")

        revokeGoogleTokenPort.revoke(token)

        return generateTokenPort.generateTokens(user.oauthCode)
    }

    override fun facebook(token: String): Pair<String, String> {

        val subject = readFacebookInfoPort.infoByToken(token).id

        val user = readUserPort.readByOAuthCode(subject)
            ?: throw AthenaException(HttpStatus.CONFLICT, "You must be sign-up")

        return generateTokenPort.generateTokens(user.oauthCode)
    }
}