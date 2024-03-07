package athena.kanghyuk.com.infrastructure.config.cors

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOrigins = listOf("http://localhost:8080")
            allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
            allowCredentials = true
            addAllowedHeader("*")
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }
}
