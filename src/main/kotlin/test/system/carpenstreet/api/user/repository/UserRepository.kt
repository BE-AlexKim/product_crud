package test.system.carpenstreet.api.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test.system.carpenstreet.api.user.model.entity.User
import java.util.Optional
import javax.swing.text.html.Option

/**
 *packageName    : test.system.carpenstreet.api.user.repository
 * fileName       : UserRepository
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByLoginId(loginId: String): Optional<User>

    fun existsByLoginId(loginId: String): Boolean

    fun findByUuid(uuid: String): Optional<User>

}