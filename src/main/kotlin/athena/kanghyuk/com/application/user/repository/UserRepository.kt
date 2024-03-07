package athena.kanghyuk.com.application.user.repository

import athena.kanghyuk.com.application.user.entity.User
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface UserRepository: Repository<User, Long> {

    fun findByAccountId(accountID: String): User?
}
