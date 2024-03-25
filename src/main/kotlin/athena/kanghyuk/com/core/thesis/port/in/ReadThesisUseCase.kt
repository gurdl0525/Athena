package athena.kanghyuk.com.core.thesis.port.`in`

import athena.kanghyuk.com.core.thesis.dto.response.ThesesResponse

interface ReadThesisUseCase {

    fun readAllFromRecruitId(id: Long): ThesesResponse
}
