package test.system.carpenstreet.api.user.model.enums

/**
 *packageName    : test.system.carpenstreet.api.user.model
 * fileName       : UserRole
 * author         : joy58
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        joy58       최초 생성
 */
enum class UserRole(private val desc: String){
    ROLE_ADMIN("매니저"), ROLE_PARTNER("작가"), ROLE_USER("고객");

    fun getDescription() = desc
}