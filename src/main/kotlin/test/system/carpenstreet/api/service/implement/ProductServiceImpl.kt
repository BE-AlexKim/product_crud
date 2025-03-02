package test.system.carpenstreet.api.service.implement

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.model.dto.SetProductRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.enums.Language
import test.system.carpenstreet.api.repository.ProductRepository
import test.system.carpenstreet.api.service.ProductService
import test.system.carpenstreet.api.service.TranslateService
import test.system.carpenstreet.api.service.UserService
import test.system.carpenstreet.api.validator.factory.ProductValidatorFactory
import test.system.carpenstreet.comn.exception.CarpenStreetException

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

    override fun getProduct() {
        TODO("Not yet implemented")
    }

    override fun getProducts() {
        TODO("Not yet implemented")
    }

    override fun getProductDetail() {
        TODO("Not yet implemented")
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun setProduct(request: SetProductRequestDTO) {
        productValidatorFactory.createProductValidate()

        val product = productRepository.save(
            Product(
                title = request.title,
                content = request.content,
                price = request.price,
                user = userService.findByUsername()
            )
        )
        product.translations = translateService.createTranslate(product, Language.KOREAN)
    }
}