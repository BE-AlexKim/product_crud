package test.system.carpenstreet.comn.swagger.document

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.MediaType
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
    summary = "상품 저장 API",
    description = "상품을 임시 저장합니다.",
    requestBody = RequestBody(
        description = "",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "상품 임시 저장",
                        description = "상품 임시 저장 시, productId 없을 경우 ",
                        value = """
                            {
                                "postingStatus": "TEMPORARY",
                                "productId": null,
                                "productTitle": "상품 제목",
                                "productPrice": 10000,
                                "productContent": "콘텐츠 내용"
                            }
                        """
                    ),ExampleObject(
                        name = "상품 임시 재저장",
                        description = "상품 임시 저장 시, productId 있을 경우 ",
                        value = """
                            {
                                "postingStatus": "TEMPORARY",
                                "productId": 1,
                                "productTitle": "상품 제목",
                                "productPrice": 10000,
                                "productContent": "콘텐츠 내용"
                            }
                        """
                    ),ExampleObject(
                        name = "상품 저장",
                        description = "상품 저장 시, 수정 내용 또는 기존 데이터가 null일 경우 오류 발생",
                        value = """
                            {
                                "postingStatus": "SAVE_REVIEW",
                                "productId": 1,
                                "productTitle": "상품 제목",
                                "productPrice": 10000,
                                "productContent": "콘텐츠 내용"
                            }
                        """
                    )
                ]
            )
        ]
    )
)
annotation class ProductTemporalDocument
