package test.system.carpenstreet.api.product.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *packageName    : test.system.carpenstreet.api.product.model.dto
 * fileName       : ProductReviewStartRequestDTO
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Schema(name = "상품 리뷰 시작 요청 객체")
data class ProductUnderReviewRequestDTO(
    @Schema(description = "매니저 사용자의 일련번호")
    val uuid: String
)
