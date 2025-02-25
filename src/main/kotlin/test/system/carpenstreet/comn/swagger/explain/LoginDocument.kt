package test.system.carpenstreet.comn.swagger.explain

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.MediaType
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.explain
 * fileName       : LoginDocument
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
    summary = "로그인 요청 API",
    description = "아이디 패스워드를 입력하여 JWT 토큰을 발급합니다.",
    requestBody = RequestBody(
        description = "로그인 요청 데이터",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "관리자 권한 사용자 데이터 샘플(1)",
                        value = """
                            {
                                "loginId": "rpp0321",
                                "loginPw": "1234"
                            }
                        """
                    ),ExampleObject(
                        name = "작가 권한 사용자 데이터 샘플(1)",
                        value = """
                            {
                                "loginId": "joy585",
                                "loginPw": "1234"
                            }
                        """
                    ),ExampleObject(
                        name = "고객 권한 사용자 데이터 샘플(1)",
                        value = """
                            {
                                "loginId": "showx66",
                                "loginPw": "1234"
                            }
                        """
                    )
                ]
            )
        ]
    )
)
annotation class LoginDocument()
