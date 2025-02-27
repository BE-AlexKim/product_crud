package test.system.carpenstreet.api.product.controller


import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import test.system.carpenstreet.api.product.model.dto.*
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.service.ProductService
import test.system.carpenstreet.comn.swagger.document.*

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

    /** 상품 저장 엔드포인트 **/
    @ProductTemporalDocument
    @PostMapping("/create")
    fun create(@RequestBody request: ProductTemporalRequestDTO): ResponseEntity<Boolean> {
        productService.createProduct(request)
        return ResponseEntity.ok(true)
    }

    /** 상품 정보 수정 엔드포인트 **/
    @ProductUpdateDocument
    @PutMapping("/{productId}/update")
    fun updateProduct(
        @PathVariable productId: Long,
        @RequestBody request: ProductUpdateRequestDTO
    ): ResponseEntity<Boolean> {
        productService.updateProduct(productId, request)
        return ResponseEntity.ok(true)
    }

    /** 검토 요청 엔드포인트 **/
    @ProductSubmitDocument
    @PatchMapping("/{productId}/submit")
    fun submit(
        @PathVariable productId: Long,
        @RequestBody request: ProductSubmitRequestDTO
    ): ResponseEntity<Boolean> {
        productService.submitProduct(productId, request)
        return ResponseEntity.ok(true)
    }

    /** 검토 중으로 상태변환 엔드포인트 **/
    @ProductUnderDocument
    @PatchMapping("/{productId}/under")
    fun under(@PathVariable productId: Long): ResponseEntity<Boolean> {
        productService.underReviewProduct(productId)
        return ResponseEntity.ok(true)
    }

    /** 상품 검토 거절 상태변환 엔드포인트 **/
    @ProductRejectDocument
    @PatchMapping("/{productId}/reject")
    fun reject(@PathVariable productId: Long, @RequestBody request: ProductRejectRequestDTO): ResponseEntity<Boolean> {
        productService.rejectProduct(productId, request)
        return ResponseEntity.ok(true)
    }

    /** 상품 목록 조회 엔드포인트 **/
    @ProductSearchDocument
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

    /** 상품 상세 조회 엔드포인트 **/
    @ProductSearchDetailDocument
    @GetMapping("/{productId}/detail")
    fun getProduct(@PathVariable productId: Long): ResponseEntity<Product> {
        val product = productService.getProduct(productId)
        return ResponseEntity.ok(product)
    }



}