package test.system.carpenstreet.api.model.enums

/**
 *packageName    : test.system.carpenstreet.api.model.enums
 * fileName       : ProductStatuus
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
enum class ProductStatus(private val desc: String) {

    TEMPORARY("임시 저장"),
    REQUEST("검토 요청"),
    START("검토 시작"),
    REJECT("검토 거절"),
    CLEAR("검토 완료");

}