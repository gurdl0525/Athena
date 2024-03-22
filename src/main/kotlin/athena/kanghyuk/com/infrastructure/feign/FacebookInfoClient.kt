package athena.kanghyuk.com.infrastructure.feign

import athena.kanghyuk.com.infrastructure.feign.dto.FacebookInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "FacebookInfoClient", url = "https://graph.facebook.com/v13.0")
interface FacebookInfoClient {

    @GetMapping("/me")
    fun getUserProfile(@RequestParam("access_token") accessToken: String): FacebookInfoResponse
}