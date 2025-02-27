package test.system.carpenstreet.comn.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import test.system.carpenstreet.comn.security.jwt.JwtAuthenticationFilter
import test.system.carpenstreet.comn.security.jwt.JwtTokenProvider
import test.system.carpenstreet.comn.swagger.properties.SwaggerProperties

/**
 *packageName    : test.system.carpenstreet.comn.security.config
 * fileName       : SecurityConfiguration
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */

@Configuration
@EnableWebSecurity
class SecurityConfig constructor(
    private val swaggerProperties: SwaggerProperties,
    private val jwtTokenProvider: JwtTokenProvider
){


    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth -> auth

                .requestMatchers(
                    "/api/v1/product/{productId}/submit",
                    "/api/v1/product/create",
                    "/api/v1/product/{productId}/update"
                ).hasAuthority("ROLE_PARTNER")

                .requestMatchers(
                    "/api/v1/product/{productId}/under",
                    "/api/v1/product/{productId}/update",
                    "/api/v1/product/{productId}/reject",
                ).hasAuthority("ROLE_ADMIN")

                .requestMatchers(
                    HttpMethod.POST,
                    "/api/v1/user/signup",
                    "/api/v1/user/login",
                ).permitAll()

                .requestMatchers(
                    "/configuration/**",
                    "/v2/api-docs/**",
                    "/v2/api-docs",
                    "/swagger-ui/**/*",
                    "/webjars/**",
                    "/v3/api-docs/**",
                    "/v3/api-docs",
                    "/swagger-resources/**",
                    "/static/images/**",
                    "/static/css"
                ).permitAll()

                .anyRequest().authenticated()
            }

            .exceptionHandling { handler -> handler
                .accessDeniedHandler(AccessDeniedHandler())
            }

            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )

            .addFilterBefore(
                corsFilter(),
                JwtAuthenticationFilter::class.java
            )

        return http.build()
    }

    @Bean(name = ["corsFilter"])
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun corsFilter(): CorsFilter {
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOrigins = swaggerProperties.serverUrl
        config.allowedMethods = listOf("*")
        config.allowedHeaders = listOf("*")
        config.exposedHeaders = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**",config)

        return CorsFilter(source)
    }

}