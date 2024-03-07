package athena.kanghyuk.com.infrastructure.config.filter

import athena.kanghyuk.com.infrastructure.filter.ExceptionHandlerFilter
import athena.kanghyuk.com.infrastructure.filter.JwtFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterChainConfig(
    private val jwtFilter: JwtFilter,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        httpSecurity.addFilterBefore(exceptionHandlerFilter, JwtFilter::class.java)
    }
}
