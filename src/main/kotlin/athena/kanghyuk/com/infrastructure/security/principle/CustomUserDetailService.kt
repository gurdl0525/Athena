package athena.kanghyuk.com.infrastructure.security.principle

import athena.kanghyuk.com.core.user.port.out.ReadUserPort
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailService(
    private val readUserPort: ReadUserPort
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): CustomUserDetails =
        readUserPort.readByAccountId(username!!)?.run { CustomUserDetails(this) }
            ?: throw AthenaException(HttpStatus.UNAUTHORIZED, "Wrong token because not exists token.")
}
