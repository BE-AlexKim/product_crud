package test.system.carpenstreet.api.translate.impl

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.repository.ProductRepository
import test.system.carpenstreet.api.product.service.ProductService
import test.system.carpenstreet.api.translate.model.TranslateProperties
import test.system.carpenstreet.api.translate.model.dto.TranslateResponseDTO
import test.system.carpenstreet.api.translate.model.entity.Translate
import test.system.carpenstreet.api.translate.repository.TranslateRepository
import test.system.carpenstreet.api.translate.service.TranslateService
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import java.time.LocalDateTime
import java.util.*

/**
 *packageName    : test.system.carpenstreet.api.translate.impl
 * fileName       : TranslateServiceImpl
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Service
class TranslateServiceImpl(
    private val translateProperties: TranslateProperties,
    private val translateRepository: TranslateRepository,
    private val productRepository: ProductRepository
): TranslateService {

    private val log = KotlinLogging.logger {}

    private val restTemplate = RestTemplate()

    private val languages = listOf("en", "ja", "ko")

    @Throws(CarpenStreetException::class)
    override fun callTranslateAPI(lang: String, text: String?): TranslateResponseDTO {

        try {
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON
            headers["X-Language"] = lang
            headers[HttpHeaders.AUTHORIZATION] = "Bearer ${translateProperties.secretKey}"

            val data = LinkedMultiValueMap<String, String>()
            data.set("text",text)

            val request = HttpEntity(data,headers)

            return restTemplate.postForEntity(translateProperties.translateUri, request, TranslateResponseDTO::class.java).body
                ?: throw CarpenStreetException(ErrorMessage.DEFAULT)

        }catch (ex: HttpClientErrorException) {
            log.error { ex.responseBodyAsString }
            throw CarpenStreetException(ErrorMessage.HTTP_CLIENT_ERROR)
        }catch (ex: HttpServerErrorException) {
            log.error { ex.responseBodyAsString }
            throw CarpenStreetException(ErrorMessage.HTTP_SERVER_ERROR)
        }catch (ex: RestClientException) {
            log.error { ex.message }
            throw CarpenStreetException(ErrorMessage.HTTP_NETWORK_ERROR)
        }catch (ex: Exception ) {
            log.error { ex.stackTrace }
            throw CarpenStreetException(ErrorMessage.DEFAULT)
        }
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun deleteTranslate(product: Product) {
        val deleteTranslate = translateRepository.findByProduct(product)
            .filter { it.locale != "ko" }

        translateRepository.deleteAll(deleteTranslate)
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun findByTranslateCodeAndLang(translateCode: String, lang: String): Translate {
        return translateRepository.findByTranslateCodeAndLocale(translateCode, lang)
            ?: throw CarpenStreetException(ErrorMessage.TRANSLATE_CODE_NOT_EXIST)
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun createTranslate(productId: Long) {
        val product = productRepository.findById(productId)
            .orElseThrow { throw CarpenStreetException(ErrorMessage.PRODUCT_NOT_EXIST) }

        val titleTranslateCode = generateTranslateCode()
        val contentTranslateCode = generateTranslateCode()

        // 제목 번역 & 저장
        translateAndSave(product, product.productTitle, titleTranslateCode)

        // 본문 번역 & 저장
        translateAndSave(product, product.productContent, contentTranslateCode)

        // 상품 엔티티에 번역 코드 저장
        product.productTitle = titleTranslateCode
        product.productContent = contentTranslateCode
        product.updateAt = LocalDateTime.now()

    }

    private fun translateAndSave(product: Product, text: String?, translateCode: String) {
        languages.forEach { lang ->
            val translatedText = callTranslateAPI(lang, text)
            saveTranslation(product, translateCode, lang, translatedText.text)
        }
    }

    private fun saveTranslation(product: Product, translateCode: String, lang: String, translatedText: String) {
        translateRepository.save(
            Translate(
                translateCode = translateCode,
                locale = Locale.forLanguageTag(lang).toLanguageTag(),
                message = translatedText,
                product = product
            )
        )
    }

    private fun generateTranslateCode(): String = UUID.randomUUID().toString()
}