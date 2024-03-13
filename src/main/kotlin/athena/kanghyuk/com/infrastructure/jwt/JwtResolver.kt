package athena.kanghyuk.com.infrastructure.jwt

import athena.kanghyuk.com.core.auth.port.out.ReadTokenPort
import athena.kanghyuk.com.infrastructure.env.jwt.JwtProperties
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtResolver(
    private val jwtProperties: JwtProperties,
    private val readTokenPort: ReadTokenPort
) {
    fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(jwtProperties.header)?.let {

            if (!it.startsWith(jwtProperties.prefix)) throw AthenaException(HttpStatus.UNAUTHORIZED, "Wrong token prefix.")

            readTokenPort.readByAccessToken(it.substring(jwtProperties.prefix.length))?.subject
                ?: throw AthenaException(HttpStatus.UNAUTHORIZED, "Wrong token because not exists token.")
        }
}
