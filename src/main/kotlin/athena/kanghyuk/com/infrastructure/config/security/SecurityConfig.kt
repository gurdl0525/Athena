package athena.kanghyuk.com.infrastructure.config.security

import athena.kanghyuk.com.infrastructure.config.filter.FilterChainConfig
import athena.kanghyuk.com.infrastructure.error.handler.CustomAuthenticationEntryPoint
import athena.kanghyuk.com.infrastructure.filter.ExceptionHandlerFilter
import athena.kanghyuk.com.infrastructure.filter.JwtFilter
import athena.kanghyuk.com.infrastructure.jwt.JwtAdapter
import athena.kanghyuk.com.infrastructure.jwt.JwtResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val jwtResolver: JwtResolver,
    private val jwtAdapter: JwtAdapter,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .formLogin().disable()
            .csrf().disable()
            .cors().and()
            //.requiresChannel().anyRequest().requiresSecure().and()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()

            .authorizeRequests()
            .antMatchers("/").permitAll()
            .anyRequest().permitAll()
            .and()

            .headers().frameOptions().sameOrigin()
            .and()

            .apply(FilterChainConfig(JwtFilter(jwtResolver, jwtAdapter), exceptionHandlerFilter)).and()
            .build()
}