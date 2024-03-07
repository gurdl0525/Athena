package athena.kanghyuk.com.infrastructure.security.principle

import athena.kanghyuk.com.core.user.port.out.ReadUserPort
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailService(
    private val readUserPort: ReadUserPort
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): CustomUserDetails =
        readUserPort.readByAccountId(username!!)?.run { CustomUserDetails(this) } ?: throw TODO()
}
