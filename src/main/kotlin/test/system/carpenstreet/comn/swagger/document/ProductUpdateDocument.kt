package test.system.carpenstreet.comn.swagger.document

import io.swagger.v3.oas.annotations.Operation
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.document
 * fileName       : ProductUpdateDocument
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
    summary = "상품 정보 수정 API",
    description = "작가의 경우 임시저장(TEMPORARY)상태의 경우 수정이 자유롭습니다.<br>" +
            "매니저의 경우 검토가 거절된 상품의 내용을 수정할 수 있습니다.<br>" +
            "작가는 검토가 완료된 상품도 수정하여 검토 요청 상태로 매니저에게 요청할 수 있습니다."
)
annotation class ProductUpdateDocument()
