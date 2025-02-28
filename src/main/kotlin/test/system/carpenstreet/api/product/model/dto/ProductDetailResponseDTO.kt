package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductDetailResponseDTO
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@Schema(name = "상품 상세 조회 응답 객체")
data class ProductDetailResponseDTO(
    val productTitle: String? = null,
    val productContent: String? = null,
    val productPrice: Int? = null,
    val creatorName: String? = null
)
