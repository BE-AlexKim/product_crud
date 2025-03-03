package test.system.carpenstreet.product

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.test.context.support.WithMockUser
import test.system.carpenstreet.api.model.dto.ProductGenerateRequestDTO
import test.system.carpenstreet.api.model.dto.ProductUpdateRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.entity.Translate
import test.system.carpenstreet.api.model.enums.Language
import test.system.carpenstreet.api.model.enums.ProductStatus
import test.system.carpenstreet.api.repository.ProductRepository
import test.system.carpenstreet.api.service.TranslateService
import test.system.carpenstreet.api.service.UserService
import test.system.carpenstreet.api.service.implement.ProductServiceImpl
import test.system.carpenstreet.api.validator.factory.ProductValidatorFactory
import test.system.carpenstreet.comn.exception.CarpenStreetException
import java.util.*
import kotlin.test.assertEquals


/**
 *packageName    : test.system.carpenstreet.translate
 * fileName       : TranslateServiceTest
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@SpringBootTest
@ExtendWith(MockKExtension::class)
class ProductServiceTest {

    @MockK
    private lateinit var translateService: TranslateService

    @MockK
    private lateinit var productRepository: ProductRepository

    @MockK
    private lateinit var productValidatorFactory: ProductValidatorFactory

    @MockK
    private lateinit var userService: UserService

    @InjectMockKs
    private lateinit var productService: ProductServiceImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    @WithMockUser(username = "text-user-002", roles = ["PARTNER"])
    fun `상품을 저장하고 번역을 생성하는 테스트`() {

        // Given 상품 제목과 콘텐츠 가격
        val request = ProductGenerateRequestDTO(
            title = "테스트 타이틀 상품",
            content = "테스트 상품 설명",
            price = 10000L
        )

        val fakeProduct = Product(request.title, request.content, request.price, null)

        val fakeTranslations = listOf(
            Translate(
                text = request.content,
                Language.KOREAN,
                fakeProduct
            ),
            Translate(
                text = request.title,
                Language.KOREAN,
                fakeProduct
            )
        )

        // Do
        every { productValidatorFactory.productGenerateValidator() } returns mockk()
        every { productRepository.save(any()) } returns  fakeProduct
        every { translateService.createTranslate(any(), any()) } returns fakeTranslations

        // When
        productService.setProductGenerated(request)

        // Then
        verify { productRepository.save(any()) }
        verify { translateService.createTranslate(fakeProduct, Language.KOREAN) }
    }

    @Test
    @WithMockUser(username = "test-user-003", roles = ["PARTNER"])
    fun `상품 업데이트 테스트 중 게시상태 상품 업데이트 테스트`() {

        // given
        val productId = 1L
        val existingProduct = mockk<Product>(relaxed = true).apply {
            every { id } returns productId
            every { status } returns ProductStatus.CLEAR
            every { isActive } returns true
            every { version } returns 1
            every { user } returns mockk()
        }

        val newProduct = mockk<Product>(relaxed = true).apply {
            every { title } returns "Updated Title"
            every { content } returns "Updated Content"
            every { price } returns 10000
            every { status } returns ProductStatus.REQUEST
            every { version } returns 2
        }

        every { productRepository.findById(productId) } returns Optional.of(existingProduct)
        every { productRepository.save(any()) } returns newProduct
        every { productValidatorFactory.productUpdateValidator(any()) } just Runs
        every { translateService.createTranslate(any(), any()) } returns emptyList()

        val request = ProductUpdateRequestDTO(
            title = "Updated Title",
            content = "Updated Content",
            price = 10000
        )

        // when
        productService.updateProduct(productId, request)

        // then
        verify(exactly = 1) { productRepository.save(any()) }
        verify(exactly = 1) { translateService.createTranslate(any(), Language.KOREAN) }
    }

    @Test
    @WithMockUser(roles = ["PARTNER"], username = "test-user-002")
    fun `임시 저장 상태의 상품은 자유로운 수정 가능`() {
        // given
        val product = Product(
            id = 2L,
            title = "OLD TITLE",
            content = "OLD Content",
            price = 10000,
            status = ProductStatus.TEMPORARY,
            isActive = false,
            version = 1,
            user = mockk()
        )

        every { productRepository.findById(2L) } returns Optional.of(product)
        every { productValidatorFactory.productUpdateValidator(product) } just Runs

        val request = ProductUpdateRequestDTO(
            title = "NEW TITLE",
            content = "NEW CONTENT",
            price = 20000
        )

        // when
        productService.updateProduct(2L, request)

        // then
        assertEquals("NEW TITLE", product.title)
        assertEquals("NEW CONTENT", product.content)
        assertEquals(20000,product.price)

    }

    @Test
    @WithMockUser(roles = ["ADMIN"], username = "test-user-001")
    fun `검토 거절된 상품의 상품은 매니저가 수정이 가능하다`() {
        // given
        val product = Product(
            id = 2L,
            title = "OLD TITLE",
            content = "OLD Content",
            price = 10000,
            status = ProductStatus.TEMPORARY,
            isActive = false,
            version = 1,
            user = mockk()
        )

        every { productRepository.findById(2L) } returns Optional.of(product)
        every { productValidatorFactory.productUpdateValidator(product) } just  Runs

        val request = ProductUpdateRequestDTO(
            title = "NEW TITLE",
            content = "NEW CONTENT",
            price = 20000
        )

        // when
        productService.updateProduct(2L, request)

        // then
        assertEquals("NEW TITLE", product.title)
        assertEquals("NEW CONTENT", product.content)
        assertEquals(20000,product.price)
    }

}