package test.system.carpenstreet.api.product.service

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.filter.ProductFilterFactory
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.product.validator.ProductValidatorFactory
import test.system.carpenstreet.api.product.model.entity.Product
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
    private val productFilterFactory: ProductFilterFactory
){

    @Transactional
    fun createProductValidate(request: ProductTemporalRequestDTO) {
        productValidatorFactory.productCreatedValidate(request)
    }

    @Transactional
    fun getFilterByUser(user: User): List<BooleanExpression> {
        return productFilterFactory.getFilterByUserRole(user)
    }

    @Transactional
    fun underProductFilter(product: Product) {
        productFilterFactory.underProductFilter(product)
    }

    @Transactional
    fun updateProductValidate(product: Product, request: ProductUpdateRequestDTO) {
        productValidatorFactory.productUpdateValidate(product, request)
    }

    @Transactional
    fun getProductFilter() {

    }
}