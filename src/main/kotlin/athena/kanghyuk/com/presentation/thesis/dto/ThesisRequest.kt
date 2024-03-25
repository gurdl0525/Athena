package athena.kanghyuk.com.presentation.thesis.dto

import javax.validation.constraints.NotBlank

data class ThesisRequest (

    @field:NotBlank(message = "null이 들어올 수 없습니다.")
    val thesis: String?
)
