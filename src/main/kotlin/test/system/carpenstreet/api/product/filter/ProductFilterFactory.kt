package test.system.carpenstreet.api.product.filter

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.security.AuthenticationFacade

/**
 *packageName    : test.system.carpenstreet.api.product.filter
 * fileName       : ProductSearchFilterFactory
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class ProductFilterFactory constructor(
    private val productFilters: List<ProductFilter>
) {

    @Throws(CarpenStreetException::class)
    fun getFilterByUserRole(user: User): List<BooleanExpression> {
        val applicableValidator = productFilters.filter { it.supports(user.role) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        return applicableValidator.map { it.getFilter(user) }
    }

    @Throws(CarpenStreetException::class)
    fun underProductFilter(product: Product) {
        if ( product.productPostingStatus
            !in listOf(ProductPostingStatus.ASK_REVIEW, ProductPostingStatus.REASK_REVIEW )) {
            throw CarpenStreetException(ErrorMessage.PRODUCT_DO_NOT_CHANGE_POSTING_STATUS)
        }
    }
}