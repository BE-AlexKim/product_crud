package test.system.carpenstreet.api.service.implement

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.model.dto.ProductGenerateRequestDTO
import test.system.carpenstreet.api.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.enums.Language
import test.system.carpenstreet.api.model.enums.ProductStatus
import test.system.carpenstreet.api.repository.ProductRepository
import test.system.carpenstreet.api.service.ProductService
import test.system.carpenstreet.api.service.TranslateService
import test.system.carpenstreet.api.service.UserService
import test.system.carpenstreet.api.validator.factory.ProductValidatorFactory
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import java.time.LocalDateTime

/**
 *packageName    : test.system.carpenstreet.api.service.implement
 * fileName       : ProductServiceImpl
 * author         : joy58
 * date           : 2025-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-01        joy58       최초 생성
 */
@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val translateService: TranslateService,
    private val userService: UserService,
    private val productValidatorFactory: ProductValidatorFactory
) : ProductService {

    override fun getProducts() {
        TODO("Not yet implemented")
    }

    override fun getProductDetail() {
        TODO("Not yet implemented")
    }

    @Throws(CarpenStreetException::class)
    override fun getProduct(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseGet { throw CarpenStreetException(ErrorMessage.PRODUCT_NOT_EXIST) }
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun setProductGenerated(request: ProductGenerateRequestDTO): Product {
        productValidatorFactory.productGenerateValidator()

        val product = productRepository.save(
            Product(
                title = request.title,
                content = request.content,
                price = request.price,
                user = userService.findByUsername()
            )
        )
        product.translations = translateService.createTranslate(product, Language.KOREAN)

        return product
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun updateProduct(productId: Long, request: ProductUpdateRequestDTO) {
        val product = getProduct(productId)
        productValidatorFactory.productUpdateValidator(product)

        when {
            // 게시 상태이면서, 활성화 되어 있는 상품이라면,
            product.status == ProductStatus.CLEAR && product.isActive -> {
                // 새로운 상품을 생성하고, 버전을 올리고 활성화를 비활성화처리 한 후, 검토 요청을 진행한다.
                val newProduct = productRepository.save(
                    Product(
                        title = request.title ?: product.title,
                        status = ProductStatus.REQUEST,
                        content = request.content ?: product.content,
                        price = request.price ?: product.price,
                        version = product.version + 1,
                        user = product.user
                    )
                )
                newProduct.translations = translateService.createTranslate(product, Language.KOREAN)
            }

            // 임시 저장 상태라면 수정이 자유롭다.
            product.status == ProductStatus.TEMPORARY -> {
                request.title?.let { product.title = it }
                request.content?.let { product.content = it }
                request.price?.let { product.price = it }
                product.updateAt = LocalDateTime.now()
            }

            // 검토 거절된 상품의 경우, 매니저가 상품을 수정할 수 있다.
            product.status == ProductStatus.REJECT -> {
                request.title?.let { product.title = it }
                request.content?.let { product.content = it }
                request.price?.let { product.price = it }
                product.updateAt = LocalDateTime.now()
            }

            else -> {
                throw CarpenStreetException("수정할 수 없습니다.")
            }
        }
    }
}