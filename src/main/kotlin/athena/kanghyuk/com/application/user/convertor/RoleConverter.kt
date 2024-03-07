package athena.kanghyuk.com.application.user.convertor

import athena.kanghyuk.com.application.user.entity.Role
import athena.kanghyuk.com.infrastructure.error.data.ErrorCode
import athena.kanghyuk.com.infrastructure.error.exception.AthenaException
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
internal class RoleConverter : AttributeConverter<MutableList<Role>, String> {

    private companion object {
        const val SPLIT_CHAR = ","
    }

    override fun convertToDatabaseColumn(attribute: MutableList<Role>): String {
        return attribute.joinToString(SPLIT_CHAR)
    }

    override fun convertToEntityAttribute(dbData: String): MutableList<Role> =
        dbData.split(SPLIT_CHAR).map {
            when (it) {
                Role.USER.name -> Role.USER
                Role.ADMIN.name -> Role.ADMIN
                else -> throw AthenaException(ErrorCode.INTERNAL_SERVER_ERROR)
            }
        }.toMutableList()
}