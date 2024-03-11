import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Versions.SPRING_VERSION
    id("io.spring.dependency-management") version Versions.DEPENDENCY_MANAGEMENT_VERSION
    kotlin("jvm") version Versions.JVM_VERSION
    kotlin("plugin.spring") version Versions.JVM_VERSION
    kotlin("plugin.jpa") version Versions.JVM_VERSION
}

group = "athena.kanghyuk.com"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {

    //jwr
    implementation(Dependencies.JWT)

    // security
    implementation(Dependencies.SPRING_SECURITY)

    // validation
    implementation(Dependencies.VALIDATION)

    // web
    implementation(Dependencies.SPRING_WEB)

    // jackson
    implementation(Dependencies.JACKSON)

    // reflection
    implementation(Dependencies.KOTLIN_REFLECT)

    // orm
    implementation(Dependencies.JPA)

    // db driver
    implementation(Dependencies.REDIS)
    runtimeOnly(Dependencies.MYSQL)

    // annotation processor
    annotationProcessor(Dependencies.ANNOTATION_PROCESSOR)

    // logger
    implementation(Dependencies.LOGGER)

    // feign
    implementation(Dependencies.OPEN_FEIGN)
    implementation(Dependencies.FEIGN_HTTP)

    // cloud
    implementation(Dependencies.SPRING_CLOUD)

    // test
    testImplementation(Dependencies.SPRING_BOOT_TEST)
    testImplementation(Dependencies.SPRING_SECURITY_TEST)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("org.springframework.data.redis.core.RedisHash")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("org.springframework.data.redis.core.RedisHash")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
