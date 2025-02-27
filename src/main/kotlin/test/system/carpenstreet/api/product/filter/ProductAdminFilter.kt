package test.system.carpenstreet.api.product.filter

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.entity.QProduct
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.api.user.model.enums.UserRole

/**
 *packageName    : test.system.carpenstreet.api.product.filter
 * fileName       : SearchAdminFilter
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class ProductAdminFilter: ProductFilter {

    override fun supports(role: UserRole) = role == UserRole.ROLE_ADMIN

    override fun getFilter(user: User): BooleanExpression {
        val qProduct = QProduct.product
        return qProduct.productPostingStatus.`in`(
            listOf(
                ProductPostingStatus.ASK_REVIEW,
                ProductPostingStatus.CLEAR_REVIEW,
                ProductPostingStatus.REASK_REVIEW,
                ProductPostingStatus.REJECT_REVIEW,
                ProductPostingStatus.UNDER_REVIEW,
            )
        )
    }
}