package test.system.carpenstreet.api.service

import test.system.carpenstreet.api.model.dto.ProductGenerateRequestDTO
import test.system.carpenstreet.api.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.model.entity.Product

/**
 *packageName    : test.system.carpenstreet.api.service
 * fileName       : ProductService
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
interface ProductService {

    fun getProduct()

    fun getProductDetail()

    fun getProducts()

    fun setProductGenerated(request: ProductGenerateRequestDTO): Product

    fun updateProduct(productId: Long, request: ProductUpdateRequestDTO)

}