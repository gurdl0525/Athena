package athena.kanghyuk.com.infrastructure.jwt

import athena.kanghyuk.com.application.auth.entity.AccessTokenRedisEntity
import athena.kanghyuk.com.application.auth.entity.RefreshTokenRedisEntity
import athena.kanghyuk.com.application.auth.repository.AccessTokenRepository
import athena.kanghyuk.com.application.auth.repository.RefreshTokenRepository
import athena.kanghyuk.com.infrastructure.env.jwt.JwtProperties
import athena.kanghyuk.com.infrastructure.security.principle.CustomUserDetailService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.PublicKey
import java.util.*

@Component
class JwtAdapter(
    private val jwtProperties: JwtProperties,
    private val customUserDetailService: CustomUserDetailService,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val accessTokenRepository: AccessTokenRepository
) {

    fun generateTokens(subject: String): Pair<String, String> {
        val access = generateAccessToken()

        val refresh = generateRefreshToken()

        accessTokenRepository.save(
            AccessTokenRedisEntity(
                subject = subject,
                accessToken = access,
                ttl = jwtProperties.accessExpiredExp
            )
        )

        refreshTokenRepository.save(
            RefreshTokenRedisEntity(
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
        accessTokenRepository.deleteById(subject)
        refreshTokenRepository.deleteById(subject)
    }

    fun reissue(refreshToken: String): Pair<String, String> {
        val rfToken = refreshTokenRepository.findByRfToken(refreshToken)
            ?: throw TODO()

        return generateTokens(rfToken.subject)
    }

    fun getAuthentication(subject: String): Authentication {

        val authDetails = customUserDetailService.loadUserByUsername(subject)

        return UsernamePasswordAuthenticationToken(authDetails, null, authDetails.authorities)
    }

    fun getJwtBody(token: String, publicKey: PublicKey): Claims =
        try {
            // body 불러오기
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> throw TODO()
                else -> throw TODO()
            }
        }
}
