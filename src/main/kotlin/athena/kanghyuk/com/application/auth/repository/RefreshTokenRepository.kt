package athena.kanghyuk.com.application.auth.repository

import athena.kanghyuk.com.application.auth.entity.RefreshToken
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface RefreshTokenRepository : Repository<RefreshToken, String> {

    fun findById(id: String): RefreshToken?

    fun deleteById(id: String)

    fun save(entity: RefreshToken): RefreshToken

    fun findByRfToken(rfToken: String): RefreshToken?
}
