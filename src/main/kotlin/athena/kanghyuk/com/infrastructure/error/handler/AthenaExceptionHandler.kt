package athena.kanghyuk.com.infrastructure.error.handler
import athena.kanghyuk.com.infrastructure.error.data.BindErrorResponse
import athena.kanghyuk.com.infrastructure.error.data.ErrorResponse
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import org.springframework.core.convert.ConversionFailedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class AthenaExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(e: BindException): BindErrorResponse = ErrorResponse.of(e)

    @ExceptionHandler(AthenaException::class)
    protected fun customExceptionHandle(e: AthenaException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e.errorCode), e.errorCode.status)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    protected fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<HashMap<String, Any>> {

        val errorMap = HashMap<String, Any>()

        errorMap["status"] = HttpStatus.BAD_REQUEST
        errorMap["message"] = e.localizedMessage

        return ResponseEntity(errorMap, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadable(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(HttpStatus.BAD_REQUEST, e.message ?: e.localizedMessage),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(IllegalStateException::class)
    protected fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(ConversionFailedException::class)
    protected fun handleConversionFailedException(e: ConversionFailedException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(MissingServletRequestParameterException::class)
    protected fun handleMissingParameterException(e: MissingServletRequestParameterException) = ResponseEntity(
        mapOf( "cause" to mapOf("filed" to e.parameterName, "message" to e.localizedMessage)),
        HttpStatus.BAD_REQUEST
    )
}