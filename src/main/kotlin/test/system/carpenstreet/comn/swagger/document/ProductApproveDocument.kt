package test.system.carpenstreet.comn.swagger.document

import io.swagger.v3.oas.annotations.Operation
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.document
 * fileName       : ProductApproveDocument
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Operation(
    summary = "상품 검토 완료 API",
)
annotation class ProductApproveDocument()
