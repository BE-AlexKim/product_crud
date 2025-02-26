package test.system.carpenstreet.comn.swagger.explain

import io.swagger.v3.oas.annotations.Operation
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.explain
 * fileName       : ProductCreateDocument
 * author         : joy58
 * date           : 2025-02-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        joy58       최초 생성
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Operation(
    summary = "상품 임시 저장 API",
    description = "상품을 임시 저장합니다."
)
annotation class ProductTemporalDocument
