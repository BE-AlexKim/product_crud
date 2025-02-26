package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductsResponseDTO
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Schema(name = "상품 목록 객체")
data class ProductsResponseDTO(
    val productId: Long? = null,
    val productPostingStatus: ProductPostingStatus? = null,
    val productTitle: String? = null,
    val creatorName: String? = null,
)
