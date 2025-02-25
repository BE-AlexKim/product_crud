package test.system.carpenstreet.api.user.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.system.carpenstreet.api.user.model.dto.LoginRequestDTO
import test.system.carpenstreet.api.user.model.dto.SignupRequestDTO
import test.system.carpenstreet.api.user.service.UserService
import test.system.carpenstreet.comn.security.jwt.JwtToken

/**
 *packageName    : test.system.carpenstreet.api.user.controller
 * fileName       : UserController
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Tag(name = "사용자", description = "<font size=\"4\">사용자 로그인, 회원가입 API</font>")
@RestController
@RequestMapping("/api/v1/user")
class UserController constructor(
    private val userService: UserService
){

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequestDTO) {
        userService.signup(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDTO): ResponseEntity<JwtToken> {
        val login = userService.login(request)
        return ResponseEntity.ok(login)
    }

}