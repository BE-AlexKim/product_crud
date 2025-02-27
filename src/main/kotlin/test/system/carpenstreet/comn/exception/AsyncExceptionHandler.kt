package test.system.carpenstreet.comn.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import java.lang.reflect.Method

/**
 *packageName    : test.system.carpenstreet.comn.async
 * fileName       : AsyncExceptionHandler
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
class AsyncExceptionHandler: AsyncUncaughtExceptionHandler {

    private val log = KotlinLogging.logger {}

    override fun handleUncaughtException(
        ex: Throwable,
        method: Method,
        vararg params: Any?
    ) {
        log.error{"비동기 작업 중 예외 발생! 메서드: ${method.name}, 예외 메시지: ${ex.message}"}
    }

}