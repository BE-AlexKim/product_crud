package test.system.carpenstreet.api.user.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.user.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.api.user.validator
 * fileName       : PartnerValidator
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */

@Component
class PartnerValidator: UserSignupValidator {
    override fun supports(role: UserRole) = role == UserRole.ROLE_PARTNER

    override fun validate(request: SignupRequestDTO) {
        require(!request.name.isNullOrEmpty()) { throw CarpenStreetException(ErrorMessage.NAME_REQUIRE_VALUE)}
        require(!request.phoneNumber.isNullOrEmpty()) { throw CarpenStreetException(ErrorMessage.PHONE_REQUIRE_VALUE)}

    }
}