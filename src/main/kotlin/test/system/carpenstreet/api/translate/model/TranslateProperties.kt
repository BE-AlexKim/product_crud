package test.system.carpenstreet.api.translate.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 *packageName    : test.system.carpenstreet.api.translate.model
 * fileName       : TranslateProperties
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Component
@ConfigurationProperties(prefix = "translate")
class TranslateProperties{
    lateinit var translateUri: String
    lateinit var secretKey: String
}
