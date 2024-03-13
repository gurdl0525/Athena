package athena.kanghyuk.com.core.auth.port.out

import athena.kanghyuk.com.application.auth.entity.AccessToken
import athena.kanghyuk.com.application.auth.entity.RefreshToken

interface SaveTokenPort {

    fun saveAccessToken(accessToken: AccessToken): AccessToken

    fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken
}