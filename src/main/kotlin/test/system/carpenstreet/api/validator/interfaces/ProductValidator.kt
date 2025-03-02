package test.system.carpenstreet.api.validator.interfaces

import test.system.carpenstreet.api.model.enums.ProductStatus
import test.system.carpenstreet.api.model.enums.UserRole

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

    fun supports(role: UserRole): Boolean

    fun createProductValidator(): Boolean

}