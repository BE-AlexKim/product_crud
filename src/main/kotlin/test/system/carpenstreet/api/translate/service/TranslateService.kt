package test.system.carpenstreet.api.translate.service

import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.translate.model.dto.TranslateResponseDTO
import test.system.carpenstreet.api.translate.model.entity.Translate

/**
 *packageName    : test.system.carpenstreet.api.translate.service
 * fileName       : TranslateService
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
interface TranslateService {

    fun callTranslateAPI(lang: String, text: String?): TranslateResponseDTO

    fun createTranslate(productId: Long)

    fun deleteTranslate(product: Product)

    fun findByTranslateCodeAndLang(translateCode: String, lang: String): Translate

}