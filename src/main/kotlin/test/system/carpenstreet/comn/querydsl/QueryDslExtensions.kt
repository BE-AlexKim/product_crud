package test.system.carpenstreet.comn.querydsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.ComparableExpressionBase
import org.springframework.data.domain.Sort
import test.system.carpenstreet.api.product.model.entity.QProduct

/**
 *packageName    : test.system.carpenstreet.comn.querydsl
 * fileName       : QueryDslExtensions
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
object QueryDslExtensions {

    fun Sort.toQueryDslOrder(qProduct: QProduct): Array<OrderSpecifier<*>> {
        return this.mapNotNull { order ->
            val path: ComparableExpressionBase<*>? = when (order.property) {
                "title" -> qProduct.productTitle
                "status" -> qProduct.productPostingStatus
                "name" -> qProduct.creator.name
                else -> null
            }
            path?.let {
                if (order.isAscending) it.asc() else it.desc()
            }
        }.toTypedArray()
    }

}