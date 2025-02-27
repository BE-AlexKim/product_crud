package test.system.carpenstreet.comn.async

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import test.system.carpenstreet.comn.exception.AsyncExceptionHandler
import java.util.concurrent.Executor

/**
 *packageName    : test.system.carpenstreet.comn.async
 * fileName       : AsyncConfig
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Configuration
@EnableAsync
class AsyncConfig: AsyncConfigurer {

    @Bean(name = ["translateExecutor"])
    fun asyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()

        executor.corePoolSize = 5
        executor.maxPoolSize = 10
        executor.queueCapacity = 100
        executor.setThreadNamePrefix("translateExecutor-")
        executor.initialize()

        return executor
    }

    // 🟢 비동기 실행 중 발생한 예외 처리
    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler {
        return AsyncExceptionHandler()
    }
}