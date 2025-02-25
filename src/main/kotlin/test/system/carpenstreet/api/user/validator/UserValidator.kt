package test.system.carpenstreet.api.user.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.user.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.user.model.enums.UserRole

/**
 *packageName    : test.system.carpenstreet.api.user.validator
 * fileName       : UserValidator
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
@Component
class UserValidator: UserSignupValidator {

    override fun supports(role: UserRole) = role == UserRole.ROLE_USER

    override fun validate(request: SignupRequestDTO) {}
}