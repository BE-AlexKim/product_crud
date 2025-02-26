package test.system.carpenstreet.api.product.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : TemporaryValidator
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class TemporaryValidator: ProductCreateValidator {

    override fun supports(postingStatus: ProductPostingStatus) = postingStatus == ProductPostingStatus.TEMPORARY

    override fun postingValidator(request: ProductTemporalRequestDTO) {
        //TODO 추가사항 발견 시 작성
    }
}