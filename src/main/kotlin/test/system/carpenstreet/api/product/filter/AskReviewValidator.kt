package test.system.carpenstreet.api.product.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.product.repository.ProductRepository
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.api.product.validator
 * fileName       : TemporaryValidator
 * author         : joy58
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        joy58       최초 생성
 */
@Component
class AskReviewValidator(
    private val productRepository: ProductRepository
): ProductCreateValidator {

    private val log = KotlinLogging.logger {}

    override fun supports(postingStatus: ProductPostingStatus) = postingStatus == ProductPostingStatus.ASK_REVIEW

    override fun postingValidator(request: ProductTemporalRequestDTO) {

        request.productId?.let {
            val hasProduct = productRepository.findById(it)
            if ( hasProduct.isPresent ) {
                val product = hasProduct.get()
                // require == false 일때 예외를 발생시킵니다.
                require(product.productPrice != null || request.productPrice != null) {
                    throw CarpenStreetException(ErrorMessage.PRODUCT_PRICE_NOT_NULL)
                }
                require(!product.productTitle.isNullOrEmpty() || !request.productTitle.isNullOrEmpty()) {
                    throw CarpenStreetException(ErrorMessage.PRODUCT_TITLE_NOT_NULL)
                }
                require(!product.productContent.isNullOrEmpty() || !request.productContent.isNullOrEmpty()) {
                    throw CarpenStreetException(ErrorMessage.PRODUCT_CONTENT_NOT_NULL)
                }
            }
        } ?: {
            require(!request.productContent.isNullOrEmpty()) {
                throw CarpenStreetException(ErrorMessage.PRODUCT_CONTENT_NOT_NULL)
            }
            require(!request.productTitle.isNullOrEmpty()) {
                throw CarpenStreetException(ErrorMessage.PRODUCT_TITLE_NOT_NULL)
            }
            require(request.productPrice != null ) {
                throw CarpenStreetException(ErrorMessage.PRODUCT_PRICE_NOT_NULL)
            }
        }
    }
}