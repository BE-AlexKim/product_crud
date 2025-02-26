package test.system.carpenstreet.api.product.validator

import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.entity.User

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : ProductCreateValidator
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
interface ProductCreateValidator {

    fun supports(postingStatus: ProductPostingStatus): Boolean

    fun postingValidator(request: ProductTemporalRequestDTO)

}