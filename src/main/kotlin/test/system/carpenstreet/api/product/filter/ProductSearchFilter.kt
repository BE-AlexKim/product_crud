package test.system.carpenstreet.api.product.filter

import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.domain.Pageable
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.api.user.model.enums.UserRole

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : PartnerSearchValidator
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
interface ProductSearchFilter {

    fun supports(role: UserRole): Boolean

    fun getFilter(user: User, pageable: Pageable): BooleanExpression

}