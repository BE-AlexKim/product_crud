package test.system.carpenstreet.api.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.model.dto
 * fileName       : ProductUpdateRequestDTO
 * author         : joy58
 * date           : 2025-03-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-02        joy58       최초 생성
 */
@Schema(name = "상품 업데이트 요청 객체")
data class ProductUpdateRequestDTO(
    @Schema(description = "상품 제목")
    val title: String? = null,
    @Schema(description = "상품 본문")
    val content: String? = null,
    @Schema(description = "상품 가격")
    val price: Long? = null
)
