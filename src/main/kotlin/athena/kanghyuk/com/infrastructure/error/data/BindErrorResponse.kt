package athena.kanghyuk.com.infrastructure.error.data

import org.springframework.http.HttpStatus

data class BindErrorResponse(
    val status: HttpStatus,
    val fieldError: List<Map<String, String?>>
)