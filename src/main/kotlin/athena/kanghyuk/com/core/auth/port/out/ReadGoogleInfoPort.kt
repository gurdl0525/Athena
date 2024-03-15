package athena.kanghyuk.com.core.auth.port.out

import athena.kanghyuk.com.infrastructure.feign.dto.GoogleInfoResponse

interface ReadGoogleInfoPort {
    fun infoByToken(accessToken: String): GoogleInfoResponse
}