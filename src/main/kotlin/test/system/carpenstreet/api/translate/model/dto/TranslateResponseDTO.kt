package test.system.carpenstreet.api.translate.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.translate.model.dto
 * fileName       : TranslateResponseDTO
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Schema(name = "번역 API 응답 객체")
data class TranslateResponseDTO(
    val language: String = "",
    val text: String = ""
)
