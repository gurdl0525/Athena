package athena.kanghyuk.com.application.thesis

import athena.kanghyuk.com.application.recruit.entity.Recruit
import athena.kanghyuk.com.application.thesis.entity.Thesis
import athena.kanghyuk.com.application.thesis.repository.ThesisRepository
import athena.kanghyuk.com.common.PersistenceAdapter
import athena.kanghyuk.com.core.thesis.port.out.ReadThesisPort
import athena.kanghyuk.com.core.thesis.port.out.SaveThesisPort
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class ThesisPersistenceAdapter(
    private val thesisRepository: ThesisRepository
): SaveThesisPort, ReadThesisPort {

    @Transactional(propagation = Propagation.MANDATORY)
    override fun save(entity: Thesis): Thesis = thesisRepository.save(entity)

    override fun readAllByRecruit(recruit: Recruit): List<Thesis> =
        thesisRepository.findAllByRecruit(recruit)
}