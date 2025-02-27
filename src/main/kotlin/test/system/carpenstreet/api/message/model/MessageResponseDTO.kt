package test.system.carpenstreet.api.message.model

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.message.model
 * fileName       : MessageResponseDTO
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Schema(name = "메세지 전송 서비스 응답 객체")
data class MessageResponseDTO(
    @Schema(description = "전송 결과")
    val result: String,
    @Schema(description = "전송 실패 사유")
    val reason: String
)
