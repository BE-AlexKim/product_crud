package test.system.carpenstreet.api.service.implement

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.entity.Translate
import test.system.carpenstreet.api.model.enums.Language
import test.system.carpenstreet.api.repository.TranslateRepository
import test.system.carpenstreet.api.service.TranslateService
import test.system.carpenstreet.comn.exception.CarpenStreetException

/**
 *packageName    : test.system.carpenstreet.api.service.implement
 * fileName       : TranslateServiceImpl
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
@Service
class TranslateServiceImpl(
    private val translateRepository: TranslateRepository
): TranslateService {

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun createTranslate(product: Product, language: Language): List<Translate> {

        val translates = listOf(product.title, product.content)

        return translates.map { text ->

            val translateText = if ( language != Language.KOREAN ) {
                callTranslationAPI(text, language)
            }else text

            translateRepository.save(
                Translate(translateText, language, product)
            )
        }
    }

    private fun callTranslationAPI(text: String, language: Language): String {
        return "${text}가 ${language.toLocale()} 번역되었습니다."
    }

    override fun updateTranslate(product: Product, language: Language): Translate {
        TODO("Not yet implemented")
    }

    override fun deleteTranslate(product: Product, language: Language) {
        TODO("Not yet implemented")
    }
}