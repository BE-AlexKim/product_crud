package test.system.carpenstreet.api.user.service.impl

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.user.model.dto.LoginRequestDTO
import test.system.carpenstreet.api.user.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.api.user.model.enums.UserRole
import test.system.carpenstreet.api.user.repository.UserRepository
import test.system.carpenstreet.api.user.service.UserService
import test.system.carpenstreet.api.user.validator.UserSignupValidatorFactory
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.security.jwt.JwtToken
import test.system.carpenstreet.comn.security.jwt.JwtTokenProvider
import java.util.UUID

/**
 *packageName    : test.system.carpenstreet.api.user.service.impl
 * fileName       : UserServiceImpl
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Service
class UserServiceImpl constructor(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userSignupValidatorFactory: UserSignupValidatorFactory
): UserService {

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun login(request: LoginRequestDTO): JwtToken {
        val authenticationToken = UsernamePasswordAuthenticationToken(request.loginId, request.loginPw)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        val principal = authentication.principal as User
        return jwtTokenProvider.createJwtToken(principal)
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun signup(request: SignupRequestDTO) {
        val validator = userSignupValidatorFactory.getValidator(request.roles)
        validator.validate(request) // 회원별 필수요소 검증

        userRepository.save(User(
            uuid = UUID.randomUUID().toString(),
            loginId = request.loginId,
            loginPw = passwordEncoder.encode(request.loginPw),
            phoneNumber = request.phoneNumber,
            name = request.name,
            role = UserRole.ROLE_USER
        ))
    }
}