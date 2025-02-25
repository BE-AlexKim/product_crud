package test.system.carpenstreet.api.product.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.system.carpenstreet.api.product.service.ProductService

/**
 *packageName    : test.system.carpenstreet.api.product.controller
 * fileName       : ProductController
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Tag(name = "상품", description = "상품 API 목록")
@RestController
@RequestMapping("/api/v1/product")
class ProductController constructor(
    private val productService: ProductService
){


    @PutMapping("/{productId}/submit")
    fun submit(@PathVariable productId: Long) {

    }


}