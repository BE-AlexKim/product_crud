package test.system.carpenstreet.api.product.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.repository.ProductRepository

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
    fun temporaryProduct()

    /**
     * 상품 검토 요청 ( 작가 )
     */
    fun submitProduct()

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
    fun getProducts()

    /**
     * 상품 상세 조회
     */
    fun getProduct()
}