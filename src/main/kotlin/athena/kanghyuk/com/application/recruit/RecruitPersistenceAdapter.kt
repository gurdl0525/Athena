package athena.kanghyuk.com.application.recruit

import athena.kanghyuk.com.application.recruit.entity.Recruit
import athena.kanghyuk.com.application.recruit.repository.RecruitNativeRepository
import athena.kanghyuk.com.application.recruit.repository.RecruitRepository
import athena.kanghyuk.com.common.PersistenceAdapter
import athena.kanghyuk.com.core.recruit.port.out.ReadRecruitPort
import java.time.LocalDate

@PersistenceAdapter
class RecruitPersistenceAdapter(
    private val nativeRepository: RecruitNativeRepository,
    private val repository: RecruitRepository
): ReadRecruitPort {

    override fun findByDateBetweenStartAtAndEndAt(date: LocalDate) = nativeRepository.findByDateBetween(date)

    override fun findById(id: Long): Recruit? = repository.findById(id)
}