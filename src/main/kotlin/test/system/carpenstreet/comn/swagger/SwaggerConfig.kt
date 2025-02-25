package test.system.carpenstreet.comn.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.hibernate.internal.util.collections.CollectionHelper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import test.system.carpenstreet.comn.swagger.properties.SwaggerProperties

/**
 *packageName    : test.system.carpenstreet.comn.`api-docs`.config
 * fileName       : SwaggerConfiguration
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Configuration
class SwaggerConfig constructor(
    private val swaggerProperties: SwaggerProperties
){

    private fun apiInfo(): Info {
        return Info()
            .title(swaggerProperties.title)
            .description("<h1>${swaggerProperties.description}</h2>")
            .version(swaggerProperties.version)
    }

    @Bean
    fun securityComponents(): Components {
        return Components().addSecuritySchemes("auth",
            SecurityScheme()
                .name(HttpHeaders.AUTHORIZATION)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .`in`(SecurityScheme.In.HEADER)
                .bearerFormat("Authorization")
        )
    }

    @Bean
    fun openApi(): OpenAPI {
        val securityRequirement = SecurityRequirement()
            .addList("auth", CollectionHelper.listOf("read", "write"))

        val openAPI = OpenAPI()

        swaggerProperties.serverUrl.forEach { url ->
            openAPI.addServersItem(Server().url(url))
        }

        return openAPI
            .components(securityComponents())
            .addSecurityItem(securityRequirement)
            .info(apiInfo())
    }

}