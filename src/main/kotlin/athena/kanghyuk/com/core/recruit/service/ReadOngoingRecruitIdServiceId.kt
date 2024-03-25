package athena.kanghyuk.com.core.recruit.service

import athena.kanghyuk.com.common.ReadOnlyUseCase
import athena.kanghyuk.com.core.recruit.dto.response.RecruitIdResponse
import athena.kanghyuk.com.core.recruit.port.`in`.ReadOngoingRecruitIdUseCase
import athena.kanghyuk.com.core.recruit.port.out.ReadRecruitPort
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.HttpStatus
import java.time.LocalDate

@ReadOnlyUseCase
class ReadOngoingRecruitIdServiceId(
    private val readRecruitPort: ReadRecruitPort
): ReadOngoingRecruitIdUseCase {

    override fun execute(): RecruitIdResponse {

        val id: Long = readRecruitPort.findByDateBetweenStartAtAndEndAt(LocalDate.now())?.run { id!! }
            ?: throw AthenaException(HttpStatus.NO_CONTENT, "recruit is not ongoing")

        return RecruitIdResponse(id)
    }
}