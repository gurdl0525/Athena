package athena.kanghyuk.com.application.thesis.entity

import athena.kanghyuk.com.application.TableNames
import athena.kanghyuk.com.application.recruit.entity.Recruit
import javax.persistence.*

@Entity
@Table(name = TableNames.THESIS)
class Thesis(
    id: Long?,
    content: String,
    isSelected: Boolean,
    recruit: Recruit
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long? = id
        protected set

    @Column(name = "content", nullable = false, updatable = false, unique = true, length = 23)
    var content: String = content
        protected set

    @Column(name = "is_selected", columnDefinition = "BIT(1) default 0", nullable = false)
    var isSelected: Boolean = isSelected
        protected set

    @ManyToOne(optional = false)
    @JoinColumn(name = "recruit_id", nullable = false, insertable = true, updatable = false)
    var recruit: Recruit = recruit
        protected set
}