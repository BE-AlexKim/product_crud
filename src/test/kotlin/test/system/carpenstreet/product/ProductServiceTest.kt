package test.system.carpenstreet.product

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import test.system.carpenstreet.api.product.controller.ProductController
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.product.service.ProductService


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
@AutoConfigureMockMvc
class ProductServiceTest {

    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ProductController(productService)).build()
    }


    @Test
    @WithMockUser(username = "test-user-002", roles = ["PARTNER"])
    fun `상품 생성 API 테스트`() {

        val request = ProductTemporalRequestDTO(
            postingStatus = ProductPostingStatus.TEMPORARY,
            productId = null,
            productTitle = "제목",
            productPrice = 10000,
            productContent = "콘텐츠"
        )
        val requestJson = ObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(status().isOk)
            .andExpect(content().string("true"))

        verify { productService.createProduct(any()) }
    }

}