package athena.kanghyuk.com.application.recruit.repository

import athena.kanghyuk.com.application.recruit.entity.Recruit
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface RecruitRepository: Repository<Recruit, Long?> {

    fun findById(id: Long): Recruit?
}