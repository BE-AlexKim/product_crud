package test.system.carpenstreet.api.validator.factory

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.enums.UserRole
import test.system.carpenstreet.api.validator.interfaces.ProductValidator
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.security.AuthenticationFacade

/**
 *packageName    : test.system.carpenstreet.api.validator.factory
 * fileName       : ProductValidatorFacotry
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
@Component
class ProductValidatorFactory(
    private val productValidators: List<ProductValidator>,
    private val authenticationFacade: AuthenticationFacade
) {

    @Throws(CarpenStreetException::class)
    fun productGenerateValidator() {
        val userRole = UserRole.valueOf(authenticationFacade.getUserRole() ?: "ROLE_USER" )
        val enableValidator = productValidators.filter { it.supports(userRole) }

        if ( enableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        enableValidator.forEach { it.createProductValidator() }
    }

    @Throws(CarpenStreetException::class)
    fun productUpdateValidator(product: Product) {
        val userRole = UserRole.valueOf(authenticationFacade.getUserRole() ?: "ROLE_USER" )
        val uuid = authenticationFacade.getUsername()

        if ( uuid != product.user?.uuid ) {
            throw CarpenStreetException("해당 상품의 작가가 아닙니다.")
        }

        val enableValidator = productValidators.filter { it.supports(userRole) }

        if ( enableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        enableValidator.forEach { it.updateProductValidator(product) }
    }
}