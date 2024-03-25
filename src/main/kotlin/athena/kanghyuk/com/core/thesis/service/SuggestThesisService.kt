package athena.kanghyuk.com.core.thesis.service

import athena.kanghyuk.com.application.thesis.entity.Thesis
import athena.kanghyuk.com.common.UseCase
import athena.kanghyuk.com.core.recruit.port.out.ReadRecruitPort
import athena.kanghyuk.com.core.thesis.port.`in`.SuggestThesisUseCase
import athena.kanghyuk.com.core.thesis.port.out.SaveThesisPort
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus

@UseCase
class SuggestThesisService(
    private val readRecruitPort: ReadRecruitPort,
    private val saveThesisPort: SaveThesisPort
): SuggestThesisUseCase {

    override fun execute(id: Long, thesis: String) {

        val recruit = readRecruitPort.findById(id)
            ?: throw AthenaException(HttpStatus.NOT_FOUND, "recruit not found with id")

        saveThesisPort.save(
            Thesis(
                id = null,
                content = thesis,
                isSelected = false,
                recruit = recruit
            )
        )
    }
}