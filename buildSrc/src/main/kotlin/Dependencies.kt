object Dependencies {
    private const val SPRING_BOOT_STARTER = "org.springframework.boot:spring-boot-starter"

    // Jwt
    const val JWT = "io.jsonwebtoken:jjwt:${Versions.JWT_VERSION}"

    // logger
    const val LOGGER = "io.github.microutils:kotlin-logging-jvm:${Versions.LOGGER_VERSION}"

    // orm
    const val JPA = "$SPRING_BOOT_STARTER-data-jpa"

    // db driver
    const val REDIS = "$SPRING_BOOT_STARTER-data-redis"
    const val MYSQL = "com.mysql:mysql-connector-j"

    // security
    const val SPRING_SECURITY = "$SPRING_BOOT_STARTER-security"

    // validation
    const val VALIDATION = "$SPRING_BOOT_STARTER-validation"

    // web
    const val SPRING_WEB = "$SPRING_BOOT_STARTER-web"

    // reflection
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"

    // jackson
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // annotation processor
    const val ANNOTATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"

    // spring boot test
    const val SPRING_BOOT_TEST = "$SPRING_BOOT_STARTER-test"

    // spring security test
    const val SPRING_SECURITY_TEST = "org.springframework.security:spring-security-test"
}