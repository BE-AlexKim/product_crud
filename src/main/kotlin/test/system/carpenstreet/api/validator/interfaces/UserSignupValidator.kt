package test.system.carpenstreet.api.validator.interfaces

import test.system.carpenstreet.api.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.model.enums.UserRole

/**
 *packageName    : test.system.carpenstreet.api.user.validator
 * fileName       : UserSignupValidator
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
interface UserSignupValidator {
    fun supports(role: UserRole): Boolean
    fun validate(request: SignupRequestDTO)
}