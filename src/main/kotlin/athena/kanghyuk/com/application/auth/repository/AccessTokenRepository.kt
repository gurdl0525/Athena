package athena.kanghyuk.com.application.auth.repository

import athena.kanghyuk.com.application.auth.entity.AccessToken
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface AccessTokenRepository : Repository<AccessToken, String> {

    fun findById(id: String): AccessToken?

    fun deleteById(id: String)

    fun save(entity: AccessToken): AccessToken

    fun findByAccessToken(accessToken: String): AccessToken?
}
