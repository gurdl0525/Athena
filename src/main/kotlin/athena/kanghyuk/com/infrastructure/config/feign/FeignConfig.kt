package athena.kanghyuk.com.infrastructure.config.feign

import athena.kanghyuk.com.infrastructure.error.handler.FeignClientErrorDecoder
import feign.Logger
import feign.codec.ErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@EnableFeignClients(basePackages = ["athena.kanghyuk.com.infrastructure.feign"])
@Configuration
@Import(FeignClientErrorDecoder::class)
class FeignConfig {

    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL

    @Bean
    @ConditionalOnMissingBean(value = [ErrorDecoder::class])
    fun commonFeignErrorDecoder(): FeignClientErrorDecoder = FeignClientErrorDecoder()
}
