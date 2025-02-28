package test.system.carpenstreet.api.product.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import test.system.carpenstreet.api.product.model.dto.*
import test.system.carpenstreet.api.product.model.entity.Product

/**
 *packageName    : test.system.carpenstreet.api.product.service
 * fileName       : ProductService
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Service
interface ProductService {

    /**
     * 작가 상품 등록 (임시 저장)
     */
    fun createProduct(request: ProductTemporalRequestDTO): Boolean

    /**
     * 상품 검토 요청 ( 작가 )
     */
    fun submitProduct(productId: Long, request: ProductSubmitRequestDTO): Boolean

    /**
     * 상품 검토 중 ( 매니저 )
     */
    fun underReviewProduct(productId: Long): Boolean

    /**
     * 상품 검토 완료 ( 매니저 )
     */
    fun approveProduct(productId: Long): Boolean

    /**
     * 상품 수정 ( 매니저, 작가 )
     */
    fun updateProduct(productId: Long, request: ProductUpdateRequestDTO): Boolean

    /**
     *  상품 검토 거절 ( 매니저 )
     */
    fun rejectProduct(productId: Long, request: ProductRejectRequestDTO): Boolean

    /**
     * 상품 목록 조회
     */
    fun getProducts(pageable: Pageable): Page<ProductsResponseDTO>

    /**
     * 상품 상세 정보 조회
     */
    fun getProductDetail(productId: Long, locale: String): ProductDetailResponseDTO?
    /**
     * 상품 조회
     */
    fun getProduct(productId: Long): Product
}