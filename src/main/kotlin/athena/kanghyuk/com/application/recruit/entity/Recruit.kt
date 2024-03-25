package athena.kanghyuk.com.application.recruit.entity

import athena.kanghyuk.com.application.IndexNames
import athena.kanghyuk.com.application.TableNames
import athena.kanghyuk.com.application.thesis.entity.Thesis
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import javax.persistence.*

@DynamicUpdate
@Entity(name = TableNames.RECRUIT)
@Table(uniqueConstraints = [UniqueConstraint(
    name = IndexNames.START_AT_END_AT,
    columnNames = ["start_at", "end_at"])
])
class Recruit(
    id: Long?,
    startAt: LocalDate,
    endAt: LocalDate,
    isClosed: Boolean,
    theses: MutableList<Thesis> = arrayListOf()
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = id
        protected set

    @Column(name = "start_at", nullable = false, updatable = false)
    var startAt: LocalDate = startAt
        protected set

    @Column(name = "end_at", nullable = false, updatable = false)
    var endAt: LocalDate = endAt
        protected set

    @Column(name = "is_closed", nullable = false, columnDefinition = "BIT(1) default 0")
    var isClosed: Boolean = isClosed
        protected set

    @OneToMany(mappedBy = "recruit")
    var theses: MutableList<Thesis> = theses
        protected set
}