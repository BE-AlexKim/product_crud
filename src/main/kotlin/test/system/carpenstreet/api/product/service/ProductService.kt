package test.system.carpenstreet.api.product.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.model.dto.ProductSubmitRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductsResponseDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.comn.exception.CarpenStreetException

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
     * @param request: 상품 임시등록 요청 객체
     * @return 상품 엔티티
     */
    fun temporaryProduct(request: ProductTemporalRequestDTO): Product

    /**
     * 상품 검토 요청 ( 작가 )
     */
    fun submitProduct(productId: Long, requestDTO: ProductSubmitRequestDTO)

    /**
     * 상품 검토 완료
     */
    fun approveProduct()

    /**
     *  상품 검토 거절
     */
    fun rejectProduct()

    /**
     * 상품 목록 조회
     */
    fun getProducts(uuid: String, pageable: Pageable): Page<ProductsResponseDTO>

    /**
     * 상품 상세 조회
     */
    fun getProduct(productId: Long): Product
}