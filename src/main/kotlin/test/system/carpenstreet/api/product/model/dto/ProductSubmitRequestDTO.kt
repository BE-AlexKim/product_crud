package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductSubmitRequestDTO
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Schema(name = "상품 검토요청 객체")
data class ProductSubmitRequestDTO(
    @Schema(description = "작가 요청 메세지")
    val productMessage: String? = null
)
