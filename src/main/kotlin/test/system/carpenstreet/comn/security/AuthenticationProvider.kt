package test.system.carpenstreet.comn.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.repository.UserRepository
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.comn.security.handler
 * fileName       : AuthenticationProvider
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Component
class AuthenticationProvider constructor(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userRepository: UserRepository
): org.springframework.security.authentication.AuthenticationProvider {

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name.toString()
        val password = authentication.credentials.toString()

        val isExistToUser = userRepository.findByLoginId(username)

        if ( isExistToUser.isPresent ) {
            if ( !passwordEncoder.matches(password, isExistToUser.get().password)) {
                throw CarpenStreetException(ErrorMessage.PASSWORD_NOT_MATCH)
            }
            return UsernamePasswordAuthenticationToken(isExistToUser.get(), password, isExistToUser.get().authorities)
        }else {
            throw CarpenStreetException(ErrorMessage.USER_NOT_EXIST)
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}