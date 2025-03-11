package test.system.carpenstreet.api.service

import test.system.carpenstreet.api.model.dto.ProductGenerateRequestDTO
import test.system.carpenstreet.api.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.comn.exception.CarpenStreetException

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

    @Throws(CarpenStreetException::class)
    fun getProduct(productId: Long): Product

    @Throws(CarpenStreetException::class)
    fun getProductDetail()

    @Throws(CarpenStreetException::class)
    fun getProducts()

    @Throws(CarpenStreetException::class)
    fun setProductGenerated(request: ProductGenerateRequestDTO): Product

    @Throws(CarpenStreetException::class)
    fun updateProduct(productId: Long, request: ProductUpdateRequestDTO)

}