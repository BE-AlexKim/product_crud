package test.system.carpenstreet.api.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.model.dto
 * fileName       : RequestSetProductDTO
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
@Schema(name = "상품 생성 요청 객체")
data class ProductGenerateRequestDTO(
    @Schema(description = "상품 제목")
    val title: String,
    @Schema(description = "상품 콘텐츠")
    val content: String,
    @Schema(description = "상품 가격")
    val price: Long
)
