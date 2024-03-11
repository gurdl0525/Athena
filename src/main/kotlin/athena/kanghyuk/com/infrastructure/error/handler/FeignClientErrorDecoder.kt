package athena.kanghyuk.com.infrastructure.error.handler

import feign.Response
import feign.codec.ErrorDecoder
import mu.KotlinLogging

class FeignClientErrorDecoder : ErrorDecoder {

    private companion object {
        val logger = KotlinLogging.logger {}
    }

    override fun decode(methodKey: String, response: Response): Exception {
        logger.error { "${response.status()} ${response.reason()} : $methodKey | ${response.body()}\n$response" }

        if (response.status() >= 400) {
            when (response.status()) {
                400 -> throw TODO()
                401 -> throw TODO()
                403 -> throw TODO()
                419 -> throw TODO()
                else -> throw TODO()
            }
        }

        return feign.FeignException.errorStatus(methodKey, response)
    }
}
