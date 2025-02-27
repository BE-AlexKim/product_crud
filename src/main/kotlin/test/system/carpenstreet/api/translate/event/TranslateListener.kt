package test.system.carpenstreet.api.translate.event

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.translate.model.dto.TranslateEvent
import test.system.carpenstreet.api.translate.service.TranslateService

/**
 *packageName    : test.system.carpenstreet.api.translate.event
 * fileName       : TranslateListener
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Component
class TranslateListener(
    private val translateService: TranslateService
) {

    @Async("translateExecutor")
    @EventListener
    fun handleTranslateRequest(event: TranslateEvent) {
        translateService.createTranslate(event.productId)
    }
}