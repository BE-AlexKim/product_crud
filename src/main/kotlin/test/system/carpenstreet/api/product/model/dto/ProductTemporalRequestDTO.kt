package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductRequestDTO
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
@Schema(name = "상품 임시 저장 요청 객체")
data class ProductTemporalRequestDTO(
    @Schema(description = "사용자 UUID", required = true)
    val uuid: String,
    @Schema(description = "저장 여부", required = true)
    val postingStatus: ProductPostingStatus,
    @Schema(description = "상품 일련번호", required = false)
    val productId: Long? = null,
    @Schema(description = "상품 제목", required = false)
    val productTitle: String? = null,
    @Schema(description = "상품 가격", required = false)
    val productPrice: Int? = null,
    @Schema(description = "상품 본문", required = false)
    val productContent: String? = null,
    @Schema(description = "작가 메시지", required = false)
    val productMessage: String? = null,
)
