package test.system.carpenstreet.comn.exception

import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *packageName    : test.system.carpenstreet.comn.exception
 * fileName       : ExceptionHandler
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Hidden
@RestControllerAdvice
class CustomExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    fun handleException(ex: Exception): ResponseEntity<ExceptionResponseDTO> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ExceptionResponseDTO(
                    code = ErrorMessage.DEFAULT.getCode(),
                    reason = ErrorMessage.DEFAULT.getMessage(),
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                )
            )
    }

    @ExceptionHandler(CarpenStreetException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    fun handleCarpenStreetException(ex: CarpenStreetException): ResponseEntity<ExceptionResponseDTO> {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
            .body(
                ExceptionResponseDTO(
                code = ex.getErrorCode().getCode(),
                reason = ex.getErrorCode().getMessage(),
                timestamp = ex.getErrorCode().setTimestamp()
            ))
    }

}