package test.system.carpenstreet.api.user.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.user.model.dto
 * fileName       : SignupDTO
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Schema(name = "회원가입 요청 객체")
data class SignupRequestDTO(
    @Schema(description = "로그인 아이디", required = true)
    val loginId: String,
    @Schema(description = "로그인 비밀번호", required = true)
    val loginPw: String,
    @Schema(description = "사용자 이름", required = true)
    val name: String,
    @Schema(description = "사용자 휴대폰정보")
    val phoneNumber: String
)
