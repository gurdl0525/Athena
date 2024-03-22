package athena.kanghyuk.com.infrastructure.adapter.oauth

import athena.kanghyuk.com.core.auth.port.out.ReadGoogleInfoPort
import athena.kanghyuk.com.core.auth.port.out.RevokeGoogleTokenPort
import athena.kanghyuk.com.infrastructure.feign.GoogleAuthClient
import athena.kanghyuk.com.infrastructure.feign.GoogleInfoClient
import athena.kanghyuk.com.infrastructure.feign.dto.GoogleInfoResponse
import org.springframework.stereotype.Component

@Component
class GoogleAdapter (
    private val googleInfoClient: GoogleInfoClient,
    private val googleAuthClient: GoogleAuthClient
) : ReadGoogleInfoPort, RevokeGoogleTokenPort {

    override fun revoke(token: String) {
        googleAuthClient.revokeToken(token)
    }

    override fun infoByToken(accessToken: String): GoogleInfoResponse = googleInfoClient.googleInfo(accessToken)
}