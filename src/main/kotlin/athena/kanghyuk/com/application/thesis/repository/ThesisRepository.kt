package athena.kanghyuk.com.application.thesis.repository

import athena.kanghyuk.com.application.recruit.entity.Recruit
import athena.kanghyuk.com.application.thesis.entity.Thesis
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface ThesisRepository: Repository<Thesis, Long?> {

    fun save(entity: Thesis): Thesis

    fun findAllByRecruit(recruit: Recruit): List<Thesis>
}