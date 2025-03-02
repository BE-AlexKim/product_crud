package test.system.carpenstreet.product

import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import test.system.carpenstreet.api.model.dto.SetProductRequestDTO
import test.system.carpenstreet.api.model.entity.Product
import test.system.carpenstreet.api.model.entity.Translate
import test.system.carpenstreet.api.model.enums.Language
import test.system.carpenstreet.api.repository.ProductRepository
import test.system.carpenstreet.api.service.ProductService
import test.system.carpenstreet.api.service.TranslateService
import test.system.carpenstreet.api.service.implement.ProductServiceImpl
import test.system.carpenstreet.api.validator.factory.ProductValidatorFactory


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

    @InjectMockKs
    private lateinit var productService: ProductServiceImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    @WithMockUser(username = "text-user-002", roles = ["PARTNER"])
    fun `setProduct 상품을 저장하고 번역을 생성하는 테스트`() {

        // Given 상품 제목과 콘텐츠 가격
        val request = SetProductRequestDTO(
            title = "테스트 타이틀 상품",
            content = "테스트 상품 설명",
            price = 10000L
        )

        val fakeProduct = Product(request.title, request.content, request.price)

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
        every { productValidatorFactory.createProductValidate() } returns mockk()
        every { productRepository.save(any()) } returns  fakeProduct
        every { translateService.createTranslate(any(), any()) } returns fakeTranslations

        // When
        productService.setProduct(request)

        // Then
        verify { productRepository.save(any()) }
        verify { translateService.createTranslate(fakeProduct, Language.KOREAN) }
    }

}