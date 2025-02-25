package test.system.carpenstreet.comn.swagger.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 *packageName    : test.system.carpenstreet.comn.dto
 * fileName       : SwaggerProperties
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Component
@ConfigurationProperties(prefix = "springdoc.api-info")
class SwaggerProperties {
    lateinit var title: String
    lateinit var description: String
    lateinit var version: String
    lateinit var serverUrl: List<String>
}