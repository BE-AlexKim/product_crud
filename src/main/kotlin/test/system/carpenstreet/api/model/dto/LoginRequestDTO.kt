package test.system.carpenstreet.api.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.user.model.dto
 * fileName       : LoginRequestDTO
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Schema(name = "로그인 요청 객체")
data class LoginRequestDTO(
    @Schema(description = "로그인 아이디")
    val loginId: String,
    @Schema(description = "로그인 비밀번호")
    val loginPw: String
)
