package test.system.carpenstreet.comn.swagger.document

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
 * fileName       : SignupDocument
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
    summary = "사용자 회원가입 API",
    description = "사용자 회원가입 서비스입니다.",
    requestBody = RequestBody(
        description = "사용자의 회원가입 요청 데이터",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "고객 회원가입 요청 데이터 예시",
                        value = """
                            {
                                "loginId": "carpen01",
                                "loginPw": "street001",
                                "name": "홍고객",
                                "phoneNumber": "01011112222",
                                "role": "ROLE_USER"
                            }
                        """
                    ), ExampleObject(
                        name = "작가 회원가입 요청 데이터 예시",
                        value = """
                            {
                                "loginId": "carpen02",
                                "loginPw": "street002",
                                "name": "엄작가",
                                "phoneNumber": "01033334444",
                                "role": "ROLE_PARTNER"
                            }
                        """
                    ), ExampleObject(
                        name = "관리자 회원가입 요청 데이터 예시",
                        value = """
                            {
                                "loginId": "carpen03",
                                "loginPw": "street003",
                                "name": "관리자",
                                "role": "ROLE_ADMIN"
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
        responseCode = "400",
        description = "BAD_REQUEST",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = [
                    ExampleObject(
                        name = "요청 데이터 누락 오류(1)",
                        description = "관리자 및 작가 이름 데이터 누락 오류",
                        value = """
                            {
                                "code": "JE0001",
                                "reason": "이름은 필수값입니다.",
                                "timestamp": "2024-01-01 12:59:59"
                            }
                        """
                    ),ExampleObject(
                        name = "요청 데이터 누락 오류(2)",
                        description = "중복 아이디 오류",
                        value = """
                            {
                                "code": "JE0005",
                                "reason": "이미 사용중인 아이디입니다.",
                                "timestamp": "2024-01-01 12:59:59"
                            }
                        """
                    ),ExampleObject(
                        name = "요청 데이터 누락 오류(3)",
                        description = "지원하지 않는 권한 입력 오류",
                        value = """
                            {
                                "code": "JE0003",
                                "reason": "지원하지 않는 역할입니다.",
                                "timestamp": "2024-01-01 12:59:59"
                            }
                        """
                    ),ExampleObject(
                        name = "요청 데이터 누락 오류(4)",
                        description = "휴대폰 필수 요청값 오류",
                        value = """
                            {
                                "code": "JE0002",
                                "reason": "휴대폰은 필수값입니다.",
                                "timestamp": "2024-01-01 12:59:59"
                            }
                        """
                    ),ExampleObject(
                        name = "요청 데이터 누락 오류(5)",
                        description = "이름 필수 요청값 오류",
                        value = """
                            {
                                "code": "JE0001",
                                "reason": "이름은 필수값입니다.",
                                "timestamp": "2024-01-01 12:59:59"
                            }
                        """
                    )
                ]
            )
        ]
    )
)
annotation class SignupDocument()
