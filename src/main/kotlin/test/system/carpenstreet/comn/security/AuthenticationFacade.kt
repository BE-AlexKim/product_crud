package test.system.carpenstreet.comn.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.comn.security
 * fileName       : AuthenticationFacade
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Component
class AuthenticationFacade {

    @Throws(CarpenStreetException::class)
    fun getUserRole(): String? {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse(null)
    }

    fun isEqualUserRole(userRole: UserRole): Boolean {
        val role = getUserRole()?.let { UserRole.valueOf(it) }
        return userRole == role
    }

    fun getUsername(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.name
    }

}