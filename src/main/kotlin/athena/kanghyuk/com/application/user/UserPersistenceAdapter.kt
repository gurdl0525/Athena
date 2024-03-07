package athena.kanghyuk.com.application.user

import athena.kanghyuk.com.application.user.entity.User
import athena.kanghyuk.com.application.user.repository.UserRepository
import athena.kanghyuk.com.common.PersistenceAdapter
import athena.kanghyuk.com.core.user.port.out.ReadUserPort

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserRepository
): ReadUserPort{

    override fun readByAccountId(accountId: String): User? = userRepository.findByAccountId(accountId)
}