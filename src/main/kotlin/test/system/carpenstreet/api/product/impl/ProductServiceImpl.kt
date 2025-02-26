package test.system.carpenstreet.api.product.impl

import jakarta.persistence.PersistenceException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.product.model.dto.ProductSubmitRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.product.repository.ProductRepository
import test.system.carpenstreet.api.product.service.ProductService
import test.system.carpenstreet.api.product.service.ProductValidationService
import test.system.carpenstreet.api.product.validator.ProductValidatorFactory
import test.system.carpenstreet.api.user.service.UserService
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import java.time.LocalDateTime

/**
 *packageName    : test.system.carpenstreet.api.product.service.impl
 * fileName       : ProductServiceImpl
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
@Service
class ProductServiceImpl constructor(
    private val userService: UserService,
    private val productRepository: ProductRepository,
    private val validationService: ProductValidationService
): ProductService{

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun temporaryProduct(request: ProductTemporalRequestDTO): Product {
        val user = userService.findByUuid(request.uuid)
        validationService.createProductValidate(request)

        return request.productId?.let { id ->
            val product = productRepository.findById(id).orElseThrow {
                CarpenStreetException(ErrorMessage.PRODUCT_NOT_EXIST)
            }
            product.apply {
                productPostingStatus = request.postingStatus
                request.productTitle?.let { productTitle = it }
                request.productContent?.let { productContent = it }
                request.productPrice?.let { productPrice = it }
                updateAt = LocalDateTime.now()
            }
        } ?: run {
            productRepository.save(
                Product(
                    productPostingStatus = request.postingStatus,
                    productTitle = request.productTitle,
                    productContent = request.productContent,
                    productPrice = request.productPrice,
                    creatorId = user
                )
            )
        }
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun submitProduct(productId: Long, requestDTO: ProductSubmitRequestDTO) {
        val product = getProduct(productId)
        product.productPostingStatus = ProductPostingStatus.ASK_REVIEW
        product.updateAt = LocalDateTime.now()
    }

    @Transactional
    override fun approveProduct() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun rejectProduct() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getProducts() {
        TODO("Not yet implemented")
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun getProduct(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseThrow { throw CarpenStreetException(ErrorMessage.PRODUCT_NOT_EXIST) }
    }
}