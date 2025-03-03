package test.system.carpenstreet.api.validator.implement

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.enums.ProductStatus
import test.system.carpenstreet.api.model.enums.UserRole
import test.system.carpenstreet.api.validator.interfaces.ProductValidator
import test.system.carpenstreet.api.validator.interfaces.SignupValidator
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.api.user.validator
 * fileName       : AdminValidator
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
@Component
class AdminValidator: SignupValidator, ProductValidator {

    override fun supports(role: UserRole) = role == UserRole.ROLE_ADMIN

    @Throws(CarpenStreetException::class)
    override fun signUpValidate(request: SignupRequestDTO) {
        require(!request.name.isNullOrEmpty()) { throw CarpenStreetException(ErrorMessage.NAME_REQUIRE_VALUE) }
    }

    @Throws(CarpenStreetException::class)
    override fun createProductValidator(): Boolean {
        throw CarpenStreetException(ErrorMessage.ACCESS_DENIED)
    }

    @Throws(CarpenStreetException::class)
    override fun updateProductValidator(product: Product) {
        if ( product.status != ProductStatus.REJECT ) {
            throw CarpenStreetException("관리자는 검토 거절된 상품만 수정가능합니다.")
        }
    }
}