package athena.kanghyuk.com.infrastructure.filter

import athena.kanghyuk.com.infrastructure.error.data.ErrorCode
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter(){

    private fun sendErrorResponse(
        errorCode: ErrorCode,
        response: HttpServletResponse
    ){

        response.let {
            it.status = errorCode.status.value()
            it.contentType = MediaType.APPLICATION_JSON_VALUE
            it.characterEncoding = "UTF-8"
        }

        objectMapper.writeValue(
            response.writer,
            errorCode.message
        )
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        }catch (e: AthenaException) {
            sendErrorResponse(e.errorCode, response)
        }catch (e: Exception){
            e.printStackTrace()
            sendErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, response)
        }
    }
}