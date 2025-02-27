package test.system.carpenstreet.api.product.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : UserValidator
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Component
class ProductUserValidator: ProductValidator {

    override fun supports(userRole: UserRole) = userRole == UserRole.ROLE_USER

    override fun productCreatedValidator(request: ProductTemporalRequestDTO) {
        throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
    }

    override fun productUpdateValidator(product: Product, request: ProductUpdateRequestDTO) {
        throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
    }
}