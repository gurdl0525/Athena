package athena.kanghyuk.com.application

object TableNames {

    private const val TABLE_PREFIX = "tbl_"

    // 유저
    const val USER = TABLE_PREFIX + "user"

    // 토큰
    const val ACCESS_TOKEN_TABLE = TABLE_PREFIX + "access_token"
    const val REFRESH_TOKEN_TABLE = TABLE_PREFIX + "refresh_token"

    // 공모함
    const val RECRUIT = TABLE_PREFIX + "recruit"

    // 논제
    const val THESIS = TABLE_PREFIX + "thesis"

    // 글
    const val POST = TABLE_PREFIX + "post"

    // 댓글
    const val COMMENT = TABLE_PREFIX + "comment"

    // 대댓글
    const val REPLY = TABLE_PREFIX + "reply"
}