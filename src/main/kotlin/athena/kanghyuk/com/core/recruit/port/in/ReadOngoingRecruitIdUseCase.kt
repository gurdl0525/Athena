package athena.kanghyuk.com.core.recruit.port.`in`

import athena.kanghyuk.com.core.recruit.dto.response.RecruitIdResponse

interface ReadOngoingRecruitIdUseCase {

    fun execute(): RecruitIdResponse
}
