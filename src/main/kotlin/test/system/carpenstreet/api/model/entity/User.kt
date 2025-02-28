package test.system.carpenstreet.api.model.entity

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.Comment
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import test.system.carpenstreet.api.model.enums.UserRole
import java.time.LocalDateTime
import java.util.UUID

/**
 *packageName    : test.system.carpenstreet.api.user.model.entity
 * fileName       : User
 * author         : joy58
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        joy58       최초 생성
 */
@Entity
@Table(name = "tb_user_info")
@Comment("사용자")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Comment("사용자 일련번호")
    val id: Long? = null,

    @Column(name = "uuid", nullable = false, unique = true)
    @Comment("사용자 UUID")
    val uuid: String = UUID.randomUUID().toString(),

    @Column(name = "login_id", nullable = false)
    @Comment("로그인 아이디")
    val loginId: String,

    @Column(name = "login_pw", nullable = false)
    @Comment("로그인 비밀번호")
    val loginPw: String,

    @Column(name = "user_name", nullable = false)
    @Comment("이름")
    val name: String? = null,

    @Column(name = "phone_number")
    @Comment("핸드폰번호")
    val phoneNumber: String? = null,

    @Column(name = "user_role", nullable = false)
    @Comment("사용자 권한")
    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @Column(name = "create_at")
    @Comment("최초 생성일시")
    val creatAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_at")
    @Comment("최초 수정일시")
    val updateAt: LocalDateTime? = null
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return loginPw
    }

    override fun getUsername(): String {
        return loginId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}