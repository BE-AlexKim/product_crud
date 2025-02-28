package test.system.carpenstreet.api.product.filter

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.entity.QProduct
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.api.user.model.enums.UserRole

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : SerachUserValidator
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class ProductUserFilter: ProductFilter {

    override fun supports(role: UserRole) = role == UserRole.ROLE_USER

    override fun getProductsFilter(user: User): BooleanExpression {
        val qProduct = QProduct.product
        return qProduct.productPostingStatus.eq(ProductPostingStatus.CLEAR_REVIEW)
            .and(qProduct.isActive)
    }

    override fun getProductDetailFilter(product: Product): BooleanExpression {
        val qProduct = QProduct.product
        return qProduct.productPostingStatus.eq(ProductPostingStatus.CLEAR_REVIEW)
            .and(qProduct.isActive)
    }
}