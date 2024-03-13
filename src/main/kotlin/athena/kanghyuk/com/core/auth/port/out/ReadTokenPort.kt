package athena.kanghyuk.com.core.auth.port.out

import athena.kanghyuk.com.application.auth.entity.AccessToken
import athena.kanghyuk.com.application.auth.entity.RefreshToken

interface ReadTokenPort {

    fun readByAccessToken(token: String): AccessToken?

    fun readByRefreshToken(token: String): RefreshToken?
}