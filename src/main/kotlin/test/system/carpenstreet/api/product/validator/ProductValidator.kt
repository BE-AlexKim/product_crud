package test.system.carpenstreet.api.product.validator

import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.enums.UserRole

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : ProductCreateValidator
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
interface ProductValidator {

    fun supports(userRole: UserRole): Boolean

    fun productCreatedValidator(request: ProductTemporalRequestDTO)

    fun productUpdateValidator(product: Product, request: ProductUpdateRequestDTO)

}