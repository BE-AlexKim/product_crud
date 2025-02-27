package test.system.carpenstreet.comn.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.persistence.TransactionRequiredException
import org.springframework.dao.DataIntegrityViolationException
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

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ExceptionResponseDTO> {
        log.error { "예외 발생 :::: " + ex.stackTraceToString() }
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
    fun handleCarpenStreetException(ex: CarpenStreetException): ResponseEntity<ExceptionResponseDTO> {
        log.error { "예외 발생 :::: " + ex.stackTraceToString() }
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
            .body(
                ExceptionResponseDTO(
                code = ex.getErrorCode().getCode(),
                reason = ex.getErrorCode().getMessage(),
                timestamp = ex.getErrorCode().setTimestamp()
            ))
    }

    @ExceptionHandler(TransactionRequiredException::class)
    fun handleTransactionException(ex: TransactionRequiredException): ResponseEntity<ExceptionResponseDTO> {
        log.error { "예외 발생 :::: " + ex.stackTraceToString() }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ExceptionResponseDTO(
                    code = "TE0001",
                    reason = "트랜잭션 오류 발생",
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                )
            )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(ex: DataIntegrityViolationException): ResponseEntity<ExceptionResponseDTO> {
        log.error { "데이터 무결성 오류 ::: " + ex.stackTraceToString() }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ExceptionResponseDTO(
                    code = "DE0002",
                    reason = "데이터 무결성 오류 발생",
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                )
            )
    }

}