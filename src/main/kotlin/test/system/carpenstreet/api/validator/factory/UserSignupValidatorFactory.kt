package test.system.carpenstreet.api.validator.factory

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.validator.interfaces.UserSignupValidator
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
    private val validators: List<UserSignupValidator>
) {

    @Throws(CarpenStreetException::class)
    fun validate(request: SignupRequestDTO) {
        val applicableValidator =  validators.filter { it.supports(request.role) }

        if ( applicableValidator.isEmpty()) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_USER_ROLE)
        }

        applicableValidator.forEach { it.validate(request) }
    }
}