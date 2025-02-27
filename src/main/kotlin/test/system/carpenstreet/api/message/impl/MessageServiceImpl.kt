package test.system.carpenstreet.api.message.impl

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
import test.system.carpenstreet.api.message.model.MessageProperties
import test.system.carpenstreet.api.message.model.MessageResponseDTO
import test.system.carpenstreet.api.message.service.MessageService
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.translate.service.TranslateService
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.util.TransformUserInfoUtil

/**
 *packageName    : test.system.carpenstreet.api.message.impl
 * fileName       : MessageServiceImpl
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Service
class MessageServiceImpl(
    private val messageProperties: MessageProperties,
    private val translateService: TranslateService
): MessageService {

    private val restTemplate = RestTemplate()
    private val log = KotlinLogging.logger {}

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun sendMessageToPartnerByProduct(product: Product, postingStatus: ProductPostingStatus) {

        try {
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON
            headers[HttpHeaders.AUTHORIZATION] = "Bearer ${messageProperties.secretKey}"

            val phoneNumber = TransformUserInfoUtil.transformPhoneNumber(product.creator.phoneNumber)
                ?: throw CarpenStreetException(ErrorMessage.PHONE_NUMBER_NOT_EXIST)

            val data = LinkedMultiValueMap<String, String>()
            data.set("phone", phoneNumber)
            data.set("text", generatedMessage(postingStatus, product.productTitle!!, product.rejectMessage))

            val request = HttpEntity(data,headers)

            val response = restTemplate.postForEntity(messageProperties.notificationSendUrl, request, MessageResponseDTO::class.java).body

            if ( response?.result == "fail" ) {
                log.error { "메세지 전송 실패 ${response.reason}" }
                throw CarpenStreetException(ErrorMessage.MESSAGE_SEND_ERROR)
            }
        }catch (ex: HttpClientErrorException) {
            log.error { ex.responseBodyAsString }
            throw CarpenStreetException(ErrorMessage.HTTP_CLIENT_ERROR)
        }catch (ex: HttpServerErrorException) {
            log.error { ex.responseBodyAsString }
            throw CarpenStreetException(ErrorMessage.HTTP_SERVER_ERROR)
        }catch (ex: RestClientException ) {
            log.error { ex.message }
            throw CarpenStreetException(ErrorMessage.HTTP_NETWORK_ERROR)
        }catch (ex: Exception ) {
            log.error { ex.stackTrace }
            throw CarpenStreetException(ErrorMessage.DEFAULT)
        }
    }

    @Throws(CarpenStreetException::class)
    fun generatedMessage(status: ProductPostingStatus, title: String, message: String? = null): String {

        val productTitle = translateService.findByTranslateCodeAndLang(title,"ko")

        return when (status) {
            ProductPostingStatus.REJECT_REVIEW -> {
                "등록하신 \n$productTitle \n상품이 거절되었습니다. \n사유: ${ message ?: "없음" }"
            }
            ProductPostingStatus.CLEAR_REVIEW -> {
                "등록하신 \n제목: $productTitle \n상품의 검토가 완료되었습니다."
            }
            ProductPostingStatus.UNDER_REVIEW -> {
                "등록하신 \n제목: $productTitle \n상품의 검토가 시작되었습니다."
            }
            else -> throw CarpenStreetException(ErrorMessage.UNSUPPORTED_POSTING_STATUS)
        }
    }


}