package test.system.carpenstreet.api.service

import test.system.carpenstreet.api.model.dto.SetProductRequestDTO

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

    fun setProduct(request: SetProductRequestDTO)

}