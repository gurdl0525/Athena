package athena.kanghyuk.com.core.thesis.service

import athena.kanghyuk.com.application.thesis.entity.Thesis
import athena.kanghyuk.com.common.ReadOnlyUseCase
import athena.kanghyuk.com.core.recruit.port.out.ReadRecruitPort
import athena.kanghyuk.com.core.thesis.dto.response.ThesesResponse
import athena.kanghyuk.com.core.thesis.dto.response.ThesisResponse
import athena.kanghyuk.com.core.thesis.port.`in`.ReadThesisUseCase
import athena.kanghyuk.com.core.thesis.port.out.ReadThesisPort
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus

@ReadOnlyUseCase
class ReadThesisService(
    private val readRecruitPort: ReadRecruitPort,
    private val readThesisPort: ReadThesisPort
): ReadThesisUseCase {

    override fun readAllFromRecruitId(id: Long): ThesesResponse {

        val recruit = readRecruitPort.findById(id)
            ?: throw AthenaException(HttpStatus.NOT_FOUND, "recruit is not found with id")

        val theses: List<Thesis> = readThesisPort.readAllByRecruit(recruit)

        if (theses.isEmpty()) {
            throw AthenaException(HttpStatus.NO_CONTENT, "there is no content -> id: $id")
        }

        return ThesesResponse(
            theses.map {
                ThesisResponse(it.id!!, it.content)
            }
        )
    }
}