package test.system.carpenstreet.api.product.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.validator.ProductValidatorFactory

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
    private val productValidatorFactory: ProductValidatorFactory
){

    @Transactional
    fun createProductValidate(request: ProductTemporalRequestDTO) {
        productValidatorFactory.createValidator(request)
    }

}