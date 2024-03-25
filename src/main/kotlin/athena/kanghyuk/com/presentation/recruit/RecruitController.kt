package athena.kanghyuk.com.presentation.recruit

import athena.kanghyuk.com.core.recruit.dto.response.RecruitIdResponse
import athena.kanghyuk.com.core.recruit.port.`in`.ReadOngoingRecruitIdUseCase
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
class RecruitController(
    private val readOngoingRecruitIdUseCase: ReadOngoingRecruitIdUseCase
) {

    @GetMapping("/recruits/ongoing/id")
    fun getOngoingRecruitId(): RecruitIdResponse = readOngoingRecruitIdUseCase.execute()
}