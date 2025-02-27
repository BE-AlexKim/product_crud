package test.system.carpenstreet.comn.security.jwt

import io.jsonwebtoken.Header
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.function.ServerRequest.Headers
import test.system.carpenstreet.comn.exception.CarpenStreetException
import java.util.Optional

/**
 *packageName    : test.system.carpenstreet.comn.security.jwt
 * fileName       : JwtAuthenticationFilter
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {

    @Throws(CarpenStreetException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val hasTokenInRequest = hasHeaderToToken(request)
        if ( hasTokenInRequest.isPresent ) {
            val isValidToken = jwtTokenProvider.isValidToToken(response, hasTokenInRequest.get())
            if ( isValidToken ) {
                val authentication =  jwtTokenProvider.getAuthentication(hasTokenInRequest.get())
                SecurityContextHolder.getContext().authentication = authentication
                filterChain.doFilter(request, response)
            }
        }else {
            filterChain.doFilter(request, response)
            return
        }
    }

    /**
     * 헤더 정보에 있는 인증토큰 추출 함수
     * @param request: HttpServletRequest
     * @return Optional<토큰>
     */
    private fun hasHeaderToToken(request: HttpServletRequest): Optional<String> {
        val authorizationHeader = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
        return if ( authorizationHeader.isPresent && authorizationHeader.get().startsWith("Bearer")) {
            Optional.of(authorizationHeader.get().substring(7))
        }else {
            Optional.empty<String>()
        }
    }
}