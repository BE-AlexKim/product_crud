package test.system.carpenstreet.comn.swagger.explain

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    description = "아이디 패스워드를 입력하여 JWT 토큰을 발급합니다.<br>" +
            "계정의 UUID를 통해서 해당 계정을 식별하오니,<br> " +
            "<font size=\"4\" color=\"red\"> https://jwt.io 에서 엑세스 토큰을 복호화하여 UUID를 사용해주시길 바랍니다.</font>",
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
@ApiResponses(
    ApiResponse(
        responseCode = "200",
        description = "OK",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "통신 성공 및 JWT 토큰 발급",
                        value = """
                            {
                                "grantType": "Bearer",
                                "accessToken": "엑세스 토큰값",
                                "refreshToken": "리프레시 토큰값"
                            }
                        """
                    )
                ]
            )
        ]
    ),
    ApiResponse(
        responseCode = "401",
        description = "UNAUTHORIZED",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "로그인 관련 오류(1)",
                        description = "사용자의 로그인 아이디가 존재하지 않습니다.",
                        value = """
                            {
                                "code": "LE0002",
                                "reason": "사용자가 존재하지 않습니다.",
                                "timestamp": "2025-01-01 11:59:59"
                            }
                        """
                    ),ExampleObject(
                        name = "로그인 관련 오류(2)",
                        description = "사용자의 비밀번호가 일치하지 않습니다.",
                        value = """
                            {
                                "code": "LE0001",
                                "reason": "비밀번호가 일치하지 않습니다.",
                                "timestamp": "2025-01-01 11:59:59"
                            }
                        """
                    )
                ]
            )
        ]
    )
)
annotation class LoginDocument()
