package test.system.carpenstreet.api.model.enums

/**
 *packageName    : test.system.carpenstreet.api.model.enums
 * fileName       : Language
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
enum class Language(private val locale: String) {
    KOREAN("ko"),
    JAPAN("ja"),
    ENGLISH("en");

    fun toLocale() = locale
}