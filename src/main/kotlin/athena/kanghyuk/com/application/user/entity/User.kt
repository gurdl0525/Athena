package athena.kanghyuk.com.application.user.entity

import athena.kanghyuk.com.application.TableNames
import athena.kanghyuk.com.application.user.convertor.RoleConverter
import java.util.*
import javax.persistence.*

@Entity
@Table(name = TableNames.USER)
class User(
    id: Long?,
    uuid: UUID?,
    accountId: String,
    password: String,
    roles: MutableList<Role>
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = id
        protected set

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", columnDefinition = "BINARY(16)", nullable = false, unique = true)
    var uuid: UUID? = uuid
        protected set

    @Column(name = "account_id", nullable = false, unique = true)
    var accountId: String = accountId
        protected set

    @Column(name = "password", nullable = false, length = 60)
    var password: String = password
        protected set

    @Convert(converter = RoleConverter::class)
    @Column(name = "roles", length = 15, nullable = false)
    var roles: MutableList<Role> = roles
        protected set
}