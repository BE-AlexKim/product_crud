package test.system.carpenstreet.api.validator.implement

import org.springframework.stereotype.Component
import test.system.carpenstreet.api.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.model.enums.UserRole
import test.system.carpenstreet.api.repository.UserRepository
import test.system.carpenstreet.api.validator.interfaces.UserSignupValidator
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.api.user.validator
 * fileName       : CommonValidator
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
@Component
class CommonValidator(
    private val userRepository: UserRepository
): UserSignupValidator {

    // 모든 사용자에게 적용
    override fun supports(role: UserRole) = true

    @Throws(CarpenStreetException::class)
    override fun validate(request: SignupRequestDTO) {
        require(request.loginId.isNotEmpty()) { throw CarpenStreetException(ErrorMessage.LOGIN_ID_REQUIRE_VALUE)}
        require(!userRepository.existsByLoginId(request.loginId)) {
            throw CarpenStreetException(ErrorMessage.DUPLICATE_LOGIN_ID)
        }
    }
}