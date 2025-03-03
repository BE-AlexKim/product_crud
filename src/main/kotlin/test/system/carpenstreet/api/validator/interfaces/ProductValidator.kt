package test.system.carpenstreet.api.validator.interfaces

import test.system.carpenstreet.api.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.enums.ProductStatus
import test.system.carpenstreet.api.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException

/**
 *packageName    : test.system.carpenstreet.api.service
 * fileName       : ProductValidationService
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
interface ProductValidator {

    @Throws(CarpenStreetException::class)
    fun supports(role: UserRole): Boolean

    @Throws(CarpenStreetException::class)
    fun createProductValidator(): Boolean

    @Throws(CarpenStreetException::class)
    fun updateProductValidator(product: Product)

}