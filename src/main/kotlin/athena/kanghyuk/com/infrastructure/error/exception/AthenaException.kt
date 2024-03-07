package athena.kanghyuk.com.infrastructure.error.exception

import athena.kanghyuk.com.infrastructure.error.data.ErrorCode

open class AthenaException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)