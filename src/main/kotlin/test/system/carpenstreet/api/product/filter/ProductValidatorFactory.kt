package test.system.carpenstreet.api.product.filter

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
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
    private val productCreateValidator: List<ProductCreateValidator>
){

    fun productGeneratedValidator(request: ProductTemporalRequestDTO) {
        val applicableValidator = productCreateValidator.filter { it.supports(request.postingStatus) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_POSTING_STATUS)
        }

        applicableValidator.forEach { it.postingValidator(request) }
    }

}