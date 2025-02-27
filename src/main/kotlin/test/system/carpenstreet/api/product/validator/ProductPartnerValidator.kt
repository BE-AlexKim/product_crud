package test.system.carpenstreet.api.product.validator

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.product.repository.ProductRepository
import test.system.carpenstreet.api.user.model.enums.UserRole
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
class ProductPartnerValidator(
    private val productRepository: ProductRepository
): ProductValidator {

    private val log = KotlinLogging.logger {}

    override fun supports(userRole: UserRole) = userRole == UserRole.ROLE_PARTNER

    override fun productCreatedValidator(request: ProductTemporalRequestDTO) {

        require(request.postingStatus in listOf(ProductPostingStatus.TEMPORARY, ProductPostingStatus.SAVE_REVIEW)) {
            throw CarpenStreetException(ErrorMessage.UNSUPPORTED_POSTING_STATUS)
        }

        if ( request.postingStatus == ProductPostingStatus.SAVE_REVIEW ) {
            request.productId?.let {
                val hasProduct = productRepository.findById(it)
                if ( hasProduct.isPresent ) {
                    val product = hasProduct.get()

                    require(product.productPrice != null || request.productPrice != null) {
                        log.error { "가격 정보 누락 ::: ${ErrorMessage.PRODUCT_PRICE_NOT_NULL.getMessage()}" }
                        throw CarpenStreetException(ErrorMessage.PRODUCT_PRICE_NOT_NULL)
                    }
                    require(!product.productTitle.isNullOrEmpty() || !request.productTitle.isNullOrEmpty()) {
                        log.error { "상품 제목 정보 누락 ::: ${ErrorMessage.PRODUCT_TITLE_NOT_NULL.getMessage()}" }
                        throw CarpenStreetException(ErrorMessage.PRODUCT_TITLE_NOT_NULL)
                    }
                    require(!product.productContent.isNullOrEmpty() || !request.productContent.isNullOrEmpty()) {
                        log.error { "상품 본문 정보 누락 ::: ${ErrorMessage.PRODUCT_CONTENT_NOT_NULL.getMessage()}" }
                        throw CarpenStreetException(ErrorMessage.PRODUCT_CONTENT_NOT_NULL)
                    }
                }
            } ?: {
                require(!request.productContent.isNullOrEmpty()) {
                    log.error { "상품 본문 정보 누락 ::: ${ErrorMessage.PRODUCT_CONTENT_NOT_NULL.getMessage()}" }
                    throw CarpenStreetException(ErrorMessage.PRODUCT_CONTENT_NOT_NULL)
                }
                require(!request.productTitle.isNullOrEmpty()) {
                    log.error { "상품 제목 누락 ::: ${ErrorMessage.PRODUCT_TITLE_NOT_NULL.getMessage()}" }
                    throw CarpenStreetException(ErrorMessage.PRODUCT_TITLE_NOT_NULL)
                }
                require(request.productPrice != null ) {
                    log.error { "가격 정보 누락 ::: ${ErrorMessage.PRODUCT_PRICE_NOT_NULL.getMessage()}" }
                    throw CarpenStreetException(ErrorMessage.PRODUCT_PRICE_NOT_NULL)
                }
            }
        }
    }



    @Throws(CarpenStreetException::class)
    override fun productUpdateValidator(product: Product, request: ProductUpdateRequestDTO ) {
        if ( product.productPostingStatus !in listOf ( ProductPostingStatus.REJECT_REVIEW, ProductPostingStatus.TEMPORARY, ProductPostingStatus.CLEAR_REVIEW )) {
            throw CarpenStreetException(ErrorMessage.ACCESS_DENIED)
        }
    }
}