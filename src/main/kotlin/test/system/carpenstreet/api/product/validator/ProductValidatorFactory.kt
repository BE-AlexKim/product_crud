package test.system.carpenstreet.api.product.validator

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

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
    private val productSearchValidators: List<ProductSearchValidator>,
    private val productCreateValidator: List<ProductCreateValidator>
){

    fun searchValidator(user: User, pageable: Pageable ) {
        val applicableValidator = productSearchValidators.filter { it.supports(user.role) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        applicableValidator.forEach { it.search(user, pageable) }
    }

    fun createValidator(request: ProductTemporalRequestDTO) {
        val applicableValidator = productCreateValidator.filter { it.supports(request.postingStatus) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_POSTING_STATUS)
        }

        applicableValidator.forEach { it.postingValidator(request) }
    }

}