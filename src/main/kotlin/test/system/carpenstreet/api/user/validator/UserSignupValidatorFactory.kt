package test.system.carpenstreet.api.user.validator

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.api.user.validator
 * fileName       : UserSignupValidatorFactory
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
@Component
class UserSignupValidatorFactory(
    private val validator: List<UserSignupValidator>
) {

    @Throws(CarpenStreetException::class)
    fun getValidator(role: UserRole): UserSignupValidator {
        return validator.firstOrNull { it.supports(role)}
            ?: throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
    }
}