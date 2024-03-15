package athena.kanghyuk.com.core.auth.service

import athena.kanghyuk.com.application.user.entity.Role
import athena.kanghyuk.com.application.user.entity.User
import athena.kanghyuk.com.common.UseCase
import athena.kanghyuk.com.core.auth.port.`in`.SignUpUseCase
import athena.kanghyuk.com.core.auth.port.out.ReadGoogleInfoPort
import athena.kanghyuk.com.core.user.port.out.ExistsUserPort
import athena.kanghyuk.com.core.user.port.out.SaveUserPort
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus

@UseCase
class SignUpService(
    private val readGoogleInfoPort: ReadGoogleInfoPort,
    private val existsUserPort: ExistsUserPort,
    private val saveUserPort: SaveUserPort
): SignUpUseCase {

    override fun google(token: String) {

        val info = readGoogleInfoPort.infoByToken(token)

        if (existsUserPort.existByOAuthCode(info.sub))
            throw AthenaException(HttpStatus.CONFLICT, "Users who have already signed up")

        saveUserPort.save(
            User(
                id = null,
                uuid = null,
                nickname = info.name,
                oauthCode = info.sub,
                roles = mutableListOf(Role.USER)
            )
        )
    }
}