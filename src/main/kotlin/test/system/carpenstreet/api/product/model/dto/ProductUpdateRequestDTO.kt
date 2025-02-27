package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductUpdateRequestDTO
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Schema(name = "상품 수정 요청 객체")
data class ProductUpdateRequestDTO(
    @Schema(description = "포스팅 상태", required = true)
    val postingStatus: ProductPostingStatus,
    @Schema(description = "상품 제목", required = false)
    val productTitle: String? = null,
    @Schema(description = "상품 가격", required = false)
    val productPrice: Int? = null,
    @Schema(description = "상품 본문", required = false)
    var productContent: String? = null,
)
