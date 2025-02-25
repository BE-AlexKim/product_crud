package test.system.carpenstreet.comn.security.jwt

import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 *packageName    : test.system.carpenstreet.comn.security.dto
 * fileName       : JwtProperties
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Component
@ConfigurationProperties(prefix = "spring.jwt")
class JwtProperties {
    lateinit var properties: Properties

    class Properties {
        lateinit var secretKey: String
        lateinit var algorithm: SignatureAlgorithm
        lateinit var issuer: String
        var accessTokenExpiresTime: Long = 0L
        var refreshTokenExpiresTime: Long = 0L
    }
}