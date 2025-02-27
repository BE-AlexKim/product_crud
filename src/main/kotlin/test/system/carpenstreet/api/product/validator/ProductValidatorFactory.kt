package test.system.carpenstreet.api.product.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.security.AuthenticationFacade

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : ProductSearchValidatorFactory
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class ProductValidatorFactory constructor(
    private val productValidators: List<ProductValidator>,
    private val authenticationFacade: AuthenticationFacade
){
    /** 상품 생성 시, 유효성 체크 **/
    fun productCreatedValidate(request: ProductTemporalRequestDTO) {
        val userRole = UserRole.valueOf(authenticationFacade.getUserRole()?: "ROLE_USER")
        val applicableValidator = productValidators.filter { it.supports(userRole) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        applicableValidator.forEach { it.productCreatedValidator(request) }
    }

    /** 상품 수정 시, 유효성 체크 **/
    fun productUpdateValidate(product: Product, request: ProductUpdateRequestDTO) {
        val userRole = UserRole.valueOf(authenticationFacade.getUserRole() ?: "ROLE_USER")

        val applicableValidator = productValidators.filter { it.supports(userRole) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        applicableValidator.forEach { it.productUpdateValidator(product, request) }
    }

}