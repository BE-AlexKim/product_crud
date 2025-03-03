package test.system.carpenstreet.api.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.system.carpenstreet.api.model.dto.ProductGenerateRequestDTO
import test.system.carpenstreet.api.service.ProductService

/**
 *packageName    : test.system.carpenstreet.api.controller
 * fileName       : ProductController
 * author         : joy58
 * date           : 2025-03-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-02        joy58       최초 생성
 */
@Tag(name = "상품")
@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping("/create")
    fun create(@RequestBody request: ProductGenerateRequestDTO): ResponseEntity<Map<String,Boolean>> {
        productService.setProductGenerated(request)
        return ResponseEntity.ok(mapOf("result" to true))
    }

}