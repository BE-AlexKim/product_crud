package test.system.carpenstreet.api.service

import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.entity.Translate
import test.system.carpenstreet.api.model.enums.Language

/**
 *packageName    : test.system.carpenstreet.api.service
 * fileName       : TranslateService
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
interface TranslateService {

    fun createTranslate(product: Product, language: Language): List<Translate>

    fun updateTranslate(product: Product, language: Language): Translate

    fun deleteTranslate(product: Product, language: Language)

}