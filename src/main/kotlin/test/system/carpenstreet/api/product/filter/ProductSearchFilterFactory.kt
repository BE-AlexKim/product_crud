package test.system.carpenstreet.api.product.filter

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

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
class ProductSearchFilterFactory constructor(
    private val productSearchFilters: List<ProductSearchFilter>
) {

    fun getFilterByUserRole(user: User, pageable: Pageable): List<BooleanExpression> {
        val applicableValidator = productSearchFilters.filter { it.supports(user.role) }

        if ( applicableValidator.isEmpty() ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        return applicableValidator.map { it.getFilter(user, pageable) }
    }
}