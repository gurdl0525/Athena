package athena.kanghyuk.com.application.auth

import athena.kanghyuk.com.application.auth.entity.AccessToken
import athena.kanghyuk.com.application.auth.entity.RefreshToken
import athena.kanghyuk.com.application.auth.repository.AccessTokenRepository
import athena.kanghyuk.com.application.auth.repository.RefreshTokenRepository
import athena.kanghyuk.com.common.PersistenceAdapter
import athena.kanghyuk.com.core.auth.port.out.DeleteTokenPort
import athena.kanghyuk.com.core.auth.port.out.ReadTokenPort
import athena.kanghyuk.com.core.auth.port.out.SaveTokenPort
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class AuthPersistenceAdapter(
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository
): ReadTokenPort, SaveTokenPort, DeleteTokenPort {

    override fun readByAccessToken(token: String): AccessToken? = accessTokenRepository.findByAccessToken(token)

    override fun readByRefreshToken(token: String): RefreshToken? = refreshTokenRepository.findByRfToken(token)

    @Transactional(propagation = Propagation.MANDATORY)
    override fun saveAccessToken(accessToken: AccessToken): AccessToken =
        accessTokenRepository.save(accessToken)

    @Transactional(propagation = Propagation.MANDATORY)
    override fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken =
        refreshTokenRepository.save(refreshToken)

    @Transactional(propagation = Propagation.MANDATORY)
    override fun deleteAccessTokenBySubject(subject: String) {
        accessTokenRepository.deleteById(subject)
    }

    @Transactional(propagation = Propagation.MANDATORY)
    override fun deleteRefreshTokenBySubject(subject: String) {
        refreshTokenRepository.deleteById(subject)
    }
}