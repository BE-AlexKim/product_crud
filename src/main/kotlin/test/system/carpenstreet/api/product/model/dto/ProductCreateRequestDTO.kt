package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductCreateRequestDTO
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Schema(name = "상품 저장 요청 객체")
data class ProductCreateRequestDTO(
    val productId: Long,
    val productTitle: String,
    val productPrice: Int,
    val productContent: String
)
