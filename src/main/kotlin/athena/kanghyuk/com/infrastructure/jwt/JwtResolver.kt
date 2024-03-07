package athena.kanghyuk.com.infrastructure.jwt

import athena.kanghyuk.com.application.auth.repository.AccessTokenRepository
import athena.kanghyuk.com.infrastructure.env.jwt.JwtProperties
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtResolver(
    private val jwtProperties: JwtProperties,
    private val accessTokenRepository: AccessTokenRepository
) {
    fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(jwtProperties.header)?.let {

            if (it.startsWith(jwtProperties.prefix)) {

                val token = it.substring(jwtProperties.prefix.length).trimStart()

                accessTokenRepository.findByAccessToken(token)?.subject
                    ?: throw TODO()
            } else {
                throw TODO() // 정해진 prefix로 시작하지 않을 경우 Error반환
            }
        }
}
