package test.system.carpenstreet.comn.swagger.document

import io.swagger.v3.oas.annotations.Operation
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.document
 * fileName       : ProductUnderDocument
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Operation(
    summary = "상품 검토 중 전환 API",
    description = "매니저 권한의 사용자가 상품 검토요청중인 상품을 검토중으로 변환합니다."
)
annotation class ProductUnderDocument
