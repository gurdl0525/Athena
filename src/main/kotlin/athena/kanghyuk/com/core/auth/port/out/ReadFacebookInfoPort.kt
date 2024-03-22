package athena.kanghyuk.com.core.auth.port.out

import athena.kanghyuk.com.infrastructure.feign.dto.FacebookInfoResponse

interface ReadFacebookInfoPort {

    fun infoByToken(accessToken: String): FacebookInfoResponse
}
