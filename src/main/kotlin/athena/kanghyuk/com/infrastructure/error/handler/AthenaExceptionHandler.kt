package athena.kanghyuk.com.infrastructure.error.handler
import athena.kanghyuk.com.infrastructure.error.data.ValidationErrorResponse
import athena.kanghyuk.com.infrastructure.error.data.ErrorResponse
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class AthenaExceptionHandler {

    @ExceptionHandler(BindException::class)
    protected fun handleValidationException(e: BindException): ValidationErrorResponse = ErrorResponse.of(e)

    @ExceptionHandler(AthenaException::class)
    protected fun customExceptionHandle(e: AthenaException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e.status, e.message), e.status)

    @ExceptionHandler(ConstraintViolationException::class)
    protected fun handleValidationException(e: ConstraintViolationException): ValidationErrorResponse = ErrorResponse.of(e)
}