package athena.kanghyuk.com.infrastructure.adapter.oauth

import athena.kanghyuk.com.core.auth.port.out.ReadFacebookInfoPort
import athena.kanghyuk.com.infrastructure.feign.FacebookInfoClient
import athena.kanghyuk.com.infrastructure.feign.dto.FacebookInfoResponse
import org.springframework.stereotype.Component

@Component
class FacebookAdapter (
    private val facebookInfoClient: FacebookInfoClient
): ReadFacebookInfoPort {

    override fun infoByToken(accessToken: String): FacebookInfoResponse = facebookInfoClient.getUserProfile(accessToken)
}