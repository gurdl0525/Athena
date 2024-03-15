package athena.kanghyuk.com.application.user

import athena.kanghyuk.com.application.user.entity.User
import athena.kanghyuk.com.application.user.repository.UserRepository
import athena.kanghyuk.com.common.PersistenceAdapter
import athena.kanghyuk.com.core.user.port.out.ExistsUserPort
import athena.kanghyuk.com.core.user.port.out.ReadUserPort
import athena.kanghyuk.com.core.user.port.out.SaveUserPort
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserRepository
): ReadUserPort, SaveUserPort, ExistsUserPort {

    override fun readByOAuthCode(oauthCode: String): User? = userRepository.findByOauthCode(oauthCode)

    override fun existByOAuthCode(oauthCode: String): Boolean = userRepository.findByOauthCode(oauthCode) != null

    @Transactional(propagation = Propagation.MANDATORY)
    override fun save(user: User): User = userRepository.save(user)
}