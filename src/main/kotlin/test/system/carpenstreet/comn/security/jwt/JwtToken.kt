package test.system.carpenstreet.comn.security.jwt

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.comn.jwt
 * fileName       : JwtToken
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Schema(name = "JWT 토큰 객체")
data class JwtToken(
    val grantType: String = "Bearer",
    val accessToken: String,
    val refreshToken: String
)
