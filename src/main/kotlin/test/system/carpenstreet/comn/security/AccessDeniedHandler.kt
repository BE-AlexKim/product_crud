package test.system.carpenstreet.comn.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.exception.ExceptionResponseDTO

/**
 *packageName    : test.system.carpenstreet.comn.security
 * fileName       : AccessDeniedHandler
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class AccessDeniedHandler: org.springframework.security.web.access.AccessDeniedHandler {

    override fun handle( request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {

        val errorMessage = ErrorMessage.ACCESS_DENIED

        val objectMapper = ObjectMapper()
        response?.characterEncoding = Charsets.UTF_8.name()
        response?.status = errorMessage.getHttpStatus().value()
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.writer?.write(
            objectMapper.writeValueAsString(
                ExceptionResponseDTO(
                    code = errorMessage.getCode(),
                    reason = errorMessage.getMessage(),
                    timestamp = errorMessage.setTimestamp()
                )
            )
        )
    }
}