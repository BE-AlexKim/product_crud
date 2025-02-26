package test.system.carpenstreet.api.product.model.enums

/**
 *packageName    : test.system.carpenstreet.api.product.model.enums
 * fileName       : ProductStatus
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
enum class ProductPostingStatus(private val desc: String) {
    TEMPORARY("임시 저장"),
    ASK_REVIEW("검토 요청"),
    REASK_REVIEW("재검토 요청"),
    UNDER_REVIEW("검토 중"),
    REJECT_REVIEW("검토 거절"),
    CLEAR_REVIEW("검토 완료");

    fun description() = desc
}