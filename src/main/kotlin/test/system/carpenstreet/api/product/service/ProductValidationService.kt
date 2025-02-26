package test.system.carpenstreet.api.product.service

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.filter.ProductSearchFilterFactory
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.filter.ProductValidatorFactory
import test.system.carpenstreet.api.user.model.entity.User

/**
 *packageName    : test.system.carpenstreet.api.product.service
 * fileName       : ProductValidationService
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Service
class ProductValidationService constructor(
    private val productValidatorFactory: ProductValidatorFactory,
    private val productSearchFilterFactory: ProductSearchFilterFactory
){

    @Transactional
    fun createProductValidate(request: ProductTemporalRequestDTO) {
        productValidatorFactory.productGeneratedValidator(request)
    }

    @Transactional
    fun getFilterByUser(user: User, pageable: Pageable): List<BooleanExpression> {
        return productSearchFilterFactory.getFilterByUserRole(user, pageable)
    }

}