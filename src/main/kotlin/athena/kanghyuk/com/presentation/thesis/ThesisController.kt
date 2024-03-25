package athena.kanghyuk.com.presentation.thesis

import athena.kanghyuk.com.core.thesis.dto.response.ThesesResponse
import athena.kanghyuk.com.core.thesis.port.`in`.ReadThesisUseCase
import athena.kanghyuk.com.core.thesis.port.`in`.SuggestThesisUseCase
import athena.kanghyuk.com.presentation.thesis.dto.ThesisRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Validated
@RestController
class ThesisController(
    private val suggestThesisUseCase: SuggestThesisUseCase,
    private val readThesisUseCase: ReadThesisUseCase
) {

    @PostMapping("/recruits/recruit/{id}/thesis")
    @ResponseStatus(HttpStatus.CREATED)
    fun suggestThesis(
        @Valid
        @NotNull(message = "id는 null일 수 없습니다.")
        @Positive(message = "id는 양수입니다.")
        @PathVariable(required = false) id: Long?,
        @Valid
        @RequestBody req: ThesisRequest
    ) {
        suggestThesisUseCase.execute(id!!, req.thesis!!)
    }

    @GetMapping("/recruits/recruit/{id}/theses")
    fun getThesesFromRecruit(
        @Valid
        @NotNull(message = "id는 null일 수 없습니다.")
        @Positive(message = "id는 양수입니다.")
        @PathVariable(required = false) id: Long?
    ): ThesesResponse = readThesisUseCase.readAllFromRecruitId(id!!)
}