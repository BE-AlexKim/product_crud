package test.system.carpenstreet.api.product.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.repository.ProductRepository
import test.system.carpenstreet.api.product.service.ProductService

/**
 *packageName    : test.system.carpenstreet.api.product.service.impl
 * fileName       : ProductServiceImpl
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Service
class ProductServiceImpl constructor(
    private val productRepository: ProductRepository
): ProductService{

    @Transactional
    override fun temporaryProduct() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun submitProduct() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun approveProduct() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun rejectProduct() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getProducts() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getProduct() {
        TODO("Not yet implemented")
    }
}