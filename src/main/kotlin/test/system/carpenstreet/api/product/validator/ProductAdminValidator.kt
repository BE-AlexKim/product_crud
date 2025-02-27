package test.system.carpenstreet.api.product.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

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
class ProductAdminValidator: ProductValidator {

    override fun supports(userRole: UserRole) = userRole == UserRole.ROLE_ADMIN

    @Throws(CarpenStreetException::class)
    override fun productCreatedValidator(request: ProductTemporalRequestDTO) {
        throw CarpenStreetException(ErrorMessage.ACCESS_DENIED)
    }

    @Throws(CarpenStreetException::class)
    override fun productUpdateValidator(product: Product, request: ProductUpdateRequestDTO) {
        require(product.productPostingStatus != ProductPostingStatus.REJECT_REVIEW ) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_POSTING_STATUS)
        }
    }
}