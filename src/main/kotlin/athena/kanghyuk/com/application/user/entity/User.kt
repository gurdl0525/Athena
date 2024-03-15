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
    oauthCode: String,
    nickname: String,
    roles: MutableList<Role>
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = id
        protected set

    @Column(name = "uuid", columnDefinition = "BINARY(16)", nullable = false, unique = true)
    var uuid: UUID = uuid ?: UUID.randomUUID()
        protected set

    @Column(name = "oauth_code", nullable = false, unique = true, updatable = false, length = 60)
    var oauthCode: String = oauthCode
        protected set

    @Column(name = "nickname", nullable = false)
    var nickname: String = nickname
        protected set

    @Convert(converter = RoleConverter::class)
    @Column(name = "roles", length = 15, nullable = false)
    var roles: MutableList<Role> = roles
        protected set
}