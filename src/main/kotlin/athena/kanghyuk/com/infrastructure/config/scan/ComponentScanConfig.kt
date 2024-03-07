package athena.kanghyuk.com.infrastructure.config.scan

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["athena.kanghyuk.com"]
)
class ComponentScanConfig
