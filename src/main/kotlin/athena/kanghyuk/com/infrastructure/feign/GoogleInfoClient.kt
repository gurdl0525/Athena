package athena.kanghyuk.com.infrastructure.feign

import athena.kanghyuk.com.infrastructure.config.feign.FeignConfig
import athena.kanghyuk.com.infrastructure.feign.dto.GoogleInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "GoogleInfoClient",
    url = "https://www.googleapis.com/oauth2/v3/userinfo",
    configuration = [FeignConfig::class]
)
interface GoogleInfoClient {

    @GetMapping
    fun googleInfo(
        @RequestParam("access_token") accessToken: String,
        @RequestParam("alt") alt: String = "json"
    ): GoogleInfoResponse
}
