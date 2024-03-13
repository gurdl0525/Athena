package athena.kanghyuk.com.infrastructure.error.exception

import org.springframework.http.HttpStatus

open class AthenaException(
    val status: HttpStatus,
    override val message: String
) : RuntimeException(message)