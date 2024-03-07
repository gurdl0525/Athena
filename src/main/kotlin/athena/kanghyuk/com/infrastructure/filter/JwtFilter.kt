package athena.kanghyuk.com.infrastructure.filter

import athena.kanghyuk.com.infrastructure.jwt.JwtAdapter
import athena.kanghyuk.com.infrastructure.jwt.JwtResolver
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter (
    private val jwtResolver: JwtResolver,
    private val jwtAdapter: JwtAdapter
): OncePerRequestFilter() {

    @Throws(Exception::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        jwtResolver.resolveToken(request)?.let {
            SecurityContextHolder.getContext().authentication = jwtAdapter.getAuthentication(it)
        }
        filterChain.doFilter(request, response)
    }
}