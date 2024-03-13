package athena.kanghyuk.com.infrastructure.error.handler

import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import feign.Response
import feign.codec.ErrorDecoder
import mu.KotlinLogging
import org.springframework.http.HttpStatus

class FeignClientErrorDecoder : ErrorDecoder {

    private companion object {
        val logger = KotlinLogging.logger {}
    }

    override fun decode(methodKey: String, response: Response): Exception {

        logger.error { "${response.status()} ${response.reason()} : $methodKey | ${response.body()}\n$response" }

        if (response.status() >= 400) {
            when (response.status()) {
                400 -> throw AthenaException(HttpStatus.BAD_REQUEST, "A problem occurred while performing OAuth.")
                401 -> throw AthenaException(HttpStatus.UNAUTHORIZED, "Wrong OAuth token.")
                403 -> throw AthenaException(HttpStatus.FORBIDDEN, "Access denied while performing OAuth.")
                else -> throw AthenaException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while performing OAuth.")
            }
        }

        return feign.FeignException.errorStatus(methodKey, response)
    }
}
