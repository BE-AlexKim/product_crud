package test.system.carpenstreet.comn.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *packageName    : test.system.carpenstreet.comn.exception
 * fileName       : DefaultExceptionResolver
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
open class DefaultExceptionResolver: RuntimeException {

    private var exceptionMessageInitializer: ExceptionMessageInitializer

    private object DefaultErrorCodeHolder {
        val DEFAULT_ERROR_CODE = object : ExceptionMessageInitializer {

            override fun getCode(): String {
                return ErrorMessage.DEFAULT.getCode()
            }

            override fun getError(): String {
                return ErrorMessage.DEFAULT.getError()
            }

            override fun getHttpStatus(): HttpStatus {
                return ErrorMessage.DEFAULT.getHttpStatus()
            }

            override fun getMessage(): String {
                return ErrorMessage.DEFAULT.getMessage()
            }

            override fun defaultException(): RuntimeException {
                return DefaultExceptionResolver(ErrorMessage.DEFAULT)
            }

            override fun setException(cause: Throwable): RuntimeException {
                return DefaultExceptionResolver(ErrorMessage.DEFAULT, cause)
            }

            override fun setTimestamp(): String {
                return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }
        }
    }

    constructor(message: String) : super(message) {
        exceptionMessageInitializer = getDefaultErrorCode()
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {
        exceptionMessageInitializer = getDefaultErrorCode()
    }

    constructor(exceptionCode: ExceptionMessageInitializer) : super(exceptionCode.getMessage()) {
        this.exceptionMessageInitializer = exceptionCode
    }

    constructor(exceptionCode: ExceptionMessageInitializer, cause: Throwable) : super(exceptionCode.getMessage(), cause) {
        this.exceptionMessageInitializer = exceptionCode
    }

    open fun getErrorCode(): ExceptionMessageInitializer {
        return exceptionMessageInitializer
    }

    private fun getDefaultErrorCode(): ExceptionMessageInitializer {
        return DefaultErrorCodeHolder.DEFAULT_ERROR_CODE
    }
}