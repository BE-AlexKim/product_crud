package test.system.carpenstreet.api.user.service

import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.user.model.dto.LoginRequestDTO
import test.system.carpenstreet.api.user.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.comn.security.jwt.JwtToken


/**
 *packageName    : test.system.carpenstreet.api.user.service
 * fileName       : UserService
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
interface UserService {

    fun login(request: LoginRequestDTO): JwtToken

    fun signup(request: SignupRequestDTO)

    fun findByUuid(uuid: String): User

}