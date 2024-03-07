package athena.kanghyuk.com.infrastructure.config.scan

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(basePackages = ["athena.kanghyuk.com.infrastructure.env"])
@Configuration
class PropertiesScanConfig
