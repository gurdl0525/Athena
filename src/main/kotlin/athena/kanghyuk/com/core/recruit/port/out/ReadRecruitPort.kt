package athena.kanghyuk.com.core.recruit.port.out

import athena.kanghyuk.com.application.recruit.entity.Recruit
import java.time.LocalDate

interface ReadRecruitPort {

    fun findByDateBetweenStartAtAndEndAt(date: LocalDate): Recruit?

    fun findById(id: Long): Recruit?
}
