package test.system.carpenstreet.comn.swagger.document

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import java.lang.annotation.Inherited

/**
 *packageName    : test.system.carpenstreet.comn.swagger.explain
 * fileName       : ProductSearchDocument
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Operation(
    summary = "상품 조회 API",
    description = "사용자의 UUID를 사용해서 사용자의 권한별 조회 범위를 지정하고 각각의 권한별로 조회 세팅 범위를 다르게 합니다.<br>" +
            "페이지네이션은 page(조회할 페이지), size(조회 갯수), sortBy(소팅 대상), direction(정렬방식) <br>" +
            "을 파라미터로 정리할 수 있습니다.",
    parameters = [
        Parameter(name = "uuid", description = "사용자 UUID", required = true),
        Parameter(name = "page", description = "조회 페이지", required = false),
        Parameter(name = "size", description = "조회할 게시물 갯수", required = false),
        Parameter(name = "sortBy", description = "정렬 대상", required = false),
        Parameter(name = "direction", description = "정렬 방식", required = false),
    ]
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
                        name = "통신성공 예상 데이터",
                        value = """
                            {
                              "content": [
                                {
                                  "productId": 23,
                                  "productPostingStatus": "CLEAR_REVIEW",
                                  "productTitle": "가득한 거리_실내 7탄",
                                  "creatorName": "이수근"
                                },
                                {
                                  "productId": 24,
                                  "productPostingStatus": "CLEAR_REVIEW",
                                  "productTitle": "가득한 거리_실내 8탄",
                                  "creatorName": "이수근"
                                },
                                {
                                  "productId": 3,
                                  "productPostingStatus": "CLEAR_REVIEW",
                                  "productTitle": "낭만과 사랑이 가득한 거리_실내 3탄",
                                  "creatorName": "홍석천"
                                },
                                {
                                  "productId": 6,
                                  "productPostingStatus": "CLEAR_REVIEW",
                                  "productTitle": "낭만과 사랑이 가득한 거리_실내 6탄",
                                  "creatorName": "홍석천"
                                },
                                {
                                  "productId": 15,
                                  "productPostingStatus": "CLEAR_REVIEW",
                                  "productTitle": "사랑이 가득한 거리_실내 7탄",
                                  "creatorName": "강호동"
                                }
                              ],
                              "pageable": {
                                "pageNumber": 0,
                                "pageSize": 5,
                                "sort": {
                                  "empty": false,
                                  "sorted": true,
                                  "unsorted": false
                                },
                                "offset": 0,
                                "unpaged": false,
                                "paged": true
                              },
                              "last": false,
                              "totalPages": 5,
                              "totalElements": 24,
                              "size": 5,
                              "number": 0,
                              "sort": {
                                "empty": false,
                                "sorted": true,
                                "unsorted": false
                              },
                              "first": true,
                              "numberOfElements": 5,
                              "empty": false
                            }
                        """
                    )
                ]
            )
        ]
    )
)
annotation class ProductSearchDocument()
