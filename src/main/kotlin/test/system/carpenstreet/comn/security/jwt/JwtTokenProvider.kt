package test.system.carpenstreet.comn.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.exception.ExceptionMessageInitializer
import test.system.carpenstreet.comn.exception.ExceptionResponseDTO
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

/**
 *packageName    : test.system.carpenstreet.comn.security.provider
 * fileName       : JwtTokenProvider
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Component
class JwtTokenProvider constructor(
    private val jwtProperties: JwtProperties
) {

    private val key: Key = Keys.hmacShaKeyFor(jwtProperties.properties.secretKey.toByteArray(StandardCharsets.UTF_8))

    fun createJwtToken(user: User): JwtToken {
        val header = hashMapOf<String, Any>()
        header["typ"] = "JWT"
        header["alg"] = jwtProperties.properties.algorithm

        return JwtToken(
            accessToken = createAccessToken(header,user),
            refreshToken = createRefreshToken(header,user)
        )
    }

    private fun createAccessToken(header: HashMap<String, Any>, user: User): String {
        val issuedAt = Date() // 발급일자
        val accessTokenExpiredDate = Date(issuedAt.time + jwtProperties.properties.accessTokenExpiresTime )

        return Jwts.builder()
            .setHeader(header)
            .setSubject(user.uuid)
            .setIssuedAt(issuedAt)
            .setIssuer(jwtProperties.properties.issuer)
            .claim("roles", user.role)
            .setExpiration(accessTokenExpiredDate)
            .signWith(key, jwtProperties.properties.algorithm)
            .compact()
    }

    private fun createRefreshToken(header: HashMap<String, Any>, user: User): String {
        val issuedAt = Date() // 발급일자
        val refreshTokenExpiresDate = Date(issuedAt.time + jwtProperties.properties.refreshTokenExpiresTime )

        return Jwts.builder()
            .setHeader(header)
            .setSubject(user.uuid)
            .setIssuedAt(issuedAt)
            .setIssuer(jwtProperties.properties.issuer)
            .setExpiration(refreshTokenExpiresDate)
            .signWith(key, jwtProperties.properties.algorithm)
            .compact()
    }

    @Throws(CarpenStreetException::class)
    fun isValidToToken(response: HttpServletResponse, token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        }catch (e: SignatureException) {
            sendErrorResponse(response, ErrorMessage.INVALID_SIGNATURE)
        } catch (e: MalformedJwtException) {
            sendErrorResponse(response, ErrorMessage.INVALID_JWT_FORMAT)
        } catch (e: ExpiredJwtException) {
            sendErrorResponse(response, ErrorMessage.EXPIRED_JWT_TOKEN)
        } catch (e: UnsupportedJwtException) {
            sendErrorResponse(response, ErrorMessage.UNSUPPORTED_JWT_TOKEN)
        } catch (e: IllegalArgumentException) {
            sendErrorResponse(response, ErrorMessage.INVALID_TOKEN)
        }
        return true
    }

    @Throws(CarpenStreetException::class)
    fun getAuthentication(token: String): Authentication {
        val isDecryptClaims = decryptClaims(token)
        if ( isDecryptClaims.isPresent ) {
            val claims = isDecryptClaims.get()
            val authorities = listOf(SimpleGrantedAuthority(claims["roles"].toString()))
            val principal = org.springframework.security.core.userdetails.User(claims.subject,"",authorities)
            return UsernamePasswordAuthenticationToken(principal,"", authorities)
        }else {
            throw CarpenStreetException(ErrorMessage.DECRYPT_TOLE_FAIL)
        }
    }

    fun decryptClaims(token: String): Optional<Claims> {
        return try {
            Optional.of(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body)
        }catch (e: SignatureException) {
            Optional.empty<Claims>()
        }
    }

    private fun sendErrorResponse(response: HttpServletResponse, error: ExceptionMessageInitializer) {
        val objectMapper = ObjectMapper()
        response.characterEncoding = Charsets.UTF_8.name()
        response.status = error.getHttpStatus().value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(
            objectMapper.writeValueAsString(
                ExceptionResponseDTO(
                    code = error.getCode(),
                    reason = error.getMessage(),
                    timestamp = error.setTimestamp()
                )
            )
        )

    }
}