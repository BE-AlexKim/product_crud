package test.system.carpenstreet.config

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.system.carpenstreet.comn.security.jwt.JwtProperties

/**
 *packageName    : test.system.carpenstreet.config
 * fileName       : ConfigurationTest
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@SpringBootTest
class ConfigurationTest {

    @Autowired
    lateinit var jwtProperties: JwtProperties

    @Test
    @DisplayName("ConfigurationProperties test")
    fun configurationProperties() {
        println("secret-key ::::: ${jwtProperties.properties.secretKey}")
        println("accessTokenExpiresTime ::::: ${jwtProperties.properties.accessTokenExpiresTime}")
        println("refreshTokenExpiresTime ::::: ${jwtProperties.properties.refreshTokenExpiresTime}")
    }
}