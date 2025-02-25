package test.system.carpenstreet.comn.exception

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 *packageName    : test.system.carpenstreet.comn.exception
 * fileName       : ExceptionResponseDTO
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Schema(name = "오류 응답 객체")
data class ExceptionResponseDTO(
    @Schema(description = "오류 코드")
    val code: String,
    @Schema(description = "오류 원인")
    val reason: String,
    @Schema(description = "발생 일자")
    val timestamp: String
)
