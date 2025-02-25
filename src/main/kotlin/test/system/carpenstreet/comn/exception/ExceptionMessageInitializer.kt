package test.system.carpenstreet.comn.exception

import org.springframework.http.HttpStatus

/**
 *packageName    : test.system.carpenstreet.comn.exception.initalizer
 * fileName       : ExceptionMessageInitalizer
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
interface ExceptionMessageInitializer {

    fun getCode(): String

    fun getError(): String

    fun getHttpStatus(): HttpStatus

    fun getMessage(): String

    fun defaultException(): RuntimeException

    fun setException(cause: Throwable): RuntimeException

    fun setTimestamp(): String

}