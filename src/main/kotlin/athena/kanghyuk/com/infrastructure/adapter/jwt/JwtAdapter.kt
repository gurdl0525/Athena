package athena.kanghyuk.com.infrastructure.adapter.jwt

import athena.kanghyuk.com.application.auth.entity.AccessToken
import athena.kanghyuk.com.application.auth.entity.RefreshToken
import athena.kanghyuk.com.core.auth.port.out.DeleteTokenPort
import athena.kanghyuk.com.core.auth.port.out.GenerateTokenPort
import athena.kanghyuk.com.core.auth.port.out.ReadTokenPort
import athena.kanghyuk.com.core.auth.port.out.SaveTokenPort
import athena.kanghyuk.com.infrastructure.env.jwt.JwtProperties
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import athena.kanghyuk.com.infrastructure.security.principle.CustomUserDetailService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtAdapter(
    private val jwtProperties: JwtProperties,
    private val customUserDetailService: CustomUserDetailService,
    private val saveTokenPort: SaveTokenPort,
    private val readTokenPort: ReadTokenPort,
    private val deleteTokenPort: DeleteTokenPort
): GenerateTokenPort {

    override fun generateTokens(subject: String): Pair<String, String> {
        val access = generateAccessToken()
        val refresh = generateRefreshToken()

        saveTokenPort.saveAccessToken(
            AccessToken(
                subject = subject,
                accessToken = access,
                ttl = jwtProperties.accessExpiredExp
            )
        )

        saveTokenPort.saveRefreshToken(
            RefreshToken(
                subject = subject,
                rfToken = refresh,
                ttl = jwtProperties.refreshExpiredExp
            )
        )

        return Pair(access, refresh)
    }

    private fun generateAccessToken(): String {
        val now = Date()

        return Jwts.builder()
            .setHeaderParam("role", "access")
            .setIssuedAt(now)
            .setExpiration(Date(now.time.plus(jwtProperties.accessExpiredExp)))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
    }

    private fun generateRefreshToken(): String {
        val now = Date()

        return Jwts.builder()
            .setHeaderParam("role", "refresh")
            .setIssuedAt(now)
            .setExpiration(Date(now.time.plus(jwtProperties.refreshExpiredExp)))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
    }

    fun revoke(subject: String) {
        deleteTokenPort.deleteAccessTokenBySubject(subject)
        deleteTokenPort.deleteRefreshTokenBySubject(subject)
    }

    fun reissue(refreshToken: String): Pair<String, String> {
        val rfToken = readTokenPort.readByRefreshToken(refreshToken)
            ?: throw AthenaException(HttpStatus.UNAUTHORIZED, "Wrong token because not exists token.")

        return generateTokens(rfToken.subject)
    }

    fun getAuthentication(subject: String): Authentication {

        val authDetails = customUserDetailService.loadUserByUsername(subject)

        return UsernamePasswordAuthenticationToken(authDetails, null, authDetails.authorities)
    }
}
