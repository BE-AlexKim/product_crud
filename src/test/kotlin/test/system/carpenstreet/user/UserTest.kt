package test.system.carpenstreet.user

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 *packageName    : test.system.carpenstreet.user
 * fileName       : UserTest
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@SpringBootTest
class UserTest {

    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    @Test
    @DisplayName("password Generated")
    fun getPassword() {
        val pass = "1234"
        val password = passwordEncoder.encode(pass)

        println("password :::: $password")
    }
}