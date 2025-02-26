package test.system.carpenstreet.comn.swagger.explain

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.MediaType
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.explain
 * fileName       : SubmitProductDocument
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
    summary = "상품 검토 요청 API",
    description = "임시 저장된 상품의 검토를 요청합니다. <br>" +
            "작가 권한의 사용자만 이용가능합니다.",
    parameters = [Parameter(
        name = "productId", description = "상품 일련번호", required = true
    )],
    requestBody = RequestBody(
        description = "매니저에게 요청할 메세지",
        required = false,
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "작가 요청 메세지 내용",
                        summary = "작가의 요청 메세지가 있을 경우",
                        description = "매니저에게 요청할 작가의 메세지 내용 불필요시 입력X",
                        value = """
                            {
                                "productMessage": "메세지 내용"
                            }
                        """
                    ),ExampleObject(
                        name = "작가 요청 메세지가 없을 경우(1)",
                        summary = "작가의 요청 메세지가 없을 경우(1)",
                        description = "매니저에게 요청할 작가의 메세지 내용 불필요시 입력X",
                        value = """
                            {
                                "productMessage": null
                            }
                        """
                    ),ExampleObject(
                        name = "작가 요청 메세지가 없을 경우(2)",
                        description = "매니저에게 요청할 작가의 메세지 내용 불필요시 입력X",
                        value = """RequestBody 자체를 입력하지 않아도 됩니다."""
                    )
                ]
            )
        ]
    )
)
annotation class ProductSubmitDocument()
