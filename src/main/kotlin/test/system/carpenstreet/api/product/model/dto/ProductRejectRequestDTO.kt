package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductRejectRequestDTO
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@Schema(name = "상품 검토 거절 요청 객체")
data class ProductRejectRequestDTO(
    @Schema(description = "검토 거절 메세지", required = false)
    val message: String? = null
)
