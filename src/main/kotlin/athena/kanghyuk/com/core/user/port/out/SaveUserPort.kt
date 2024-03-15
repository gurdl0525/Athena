package athena.kanghyuk.com.core.user.port.out

import athena.kanghyuk.com.application.user.entity.User

interface SaveUserPort {

    fun save(user: User): User

}
