package test.system.carpenstreet.api.message.model

import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 *packageName    : test.system.carpenstreet.api.message.model
 * fileName       : MessageProperties
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Component
@ConfigurationProperties(prefix = "message")
class MessageProperties {
    lateinit var secretKey: String
    lateinit var from: String
    lateinit var notificationSendUrl: String
}