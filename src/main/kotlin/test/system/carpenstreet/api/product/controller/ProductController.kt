package test.system.carpenstreet.api.product.controller

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import test.system.carpenstreet.api.product.model.dto.ProductSubmitRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductTemporalRequestDTO
import test.system.carpenstreet.api.product.model.dto.ProductsResponseDTO
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.service.ProductService
import test.system.carpenstreet.comn.swagger.explain.ProductTemporalDocument
import test.system.carpenstreet.comn.swagger.explain.ProductSubmitDocument

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

    @ProductTemporalDocument
    @PostMapping("/temporary")
    fun temporary(@RequestBody request: ProductTemporalRequestDTO): ResponseEntity<Product> {
        val product = productService.temporaryProduct(request)
        return ResponseEntity.ok(product)
    }

    @ProductSubmitDocument
    @PutMapping("/{productId}/submit")
    fun submit(
        @PathVariable productId: Long,
        @RequestBody request: ProductSubmitRequestDTO = ProductSubmitRequestDTO()
    ) {
        productService.submitProduct(productId, request)
    }

    @GetMapping("/list")
    fun getProducts(
        @RequestParam uuid: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int,
        @RequestParam(defaultValue = "name") sortBy: String,
        @RequestParam(defaultValue = "ASC") direction: Sort.Direction,
    ): ResponseEntity<Page<ProductsResponseDTO>> {
        val pageable = PageRequest.of(page,size, Sort.by(direction, sortBy))
        return ResponseEntity.ok(productService.getProducts(uuid, pageable))
    }



}