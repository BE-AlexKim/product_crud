package test.system.carpenstreet.api.product.impl

import com.querydsl.core.types.Expression
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.system.carpenstreet.api.message.service.MessageService
import test.system.carpenstreet.api.product.model.dto.*
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.entity.QProduct
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.product.repository.ProductRepository
import test.system.carpenstreet.api.product.service.ProductService
import test.system.carpenstreet.api.product.service.ProductValidationService
import test.system.carpenstreet.api.translate.model.dto.TranslateEvent
import test.system.carpenstreet.api.translate.model.entity.QTranslate
import test.system.carpenstreet.api.translate.service.TranslateService
import test.system.carpenstreet.api.user.model.entity.QUser
import test.system.carpenstreet.api.user.model.entity.User
import test.system.carpenstreet.api.user.service.UserService
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage
import test.system.carpenstreet.comn.querydsl.QueryDslExtensions.toQueryDslOrder
import test.system.carpenstreet.comn.security.AuthenticationFacade
import java.time.LocalDateTime
import java.util.*

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
    private val validationService: ProductValidationService,
    private val queryFactory: JPAQueryFactory,
    private val messageService: MessageService,
    private val authenticationFacade: AuthenticationFacade,
    private val translateService: TranslateService,
    private val eventPublisher: ApplicationEventPublisher
): ProductService{

    /**
     * 작가 상품 임시저장 서비스
     * @param request: ProductTemporalRequestDTO(임시저장 요청 객체)
     * @return 등록 성공 유무
     */
    @Transactional
    @Throws(CarpenStreetException::class)
    override fun createProduct(request: ProductTemporalRequestDTO): Boolean {
        val uuid = authenticationFacade.getUsername()
        val user = userService.findByUuid(uuid)

        validationService.createProductValidate(request)

        request.productId?.let { id ->
            val product = productRepository.findById(id).orElseThrow {
                CarpenStreetException(ErrorMessage.PRODUCT_NOT_EXIST)
            }
            updateProduct(product, request)
            translateService.saveTranslationIfNeeded(product, "ko", request.productTitle, request.productContent)

        } ?: run {
            val product = createNewProduct(request, user)
            translateService.saveTranslationIfNeeded(product, "ko", request.productTitle, request.productContent)
        }
        return true
    }

    private fun updateProduct(product: Product, request: ProductTemporalRequestDTO) {
        product.apply {
            productPostingStatus = request.postingStatus
            request.productTitle?.let { productTitle = it }
            request.productContent?.let { productContent = it }
            request.productPrice?.let { productPrice = it }
            updateAt = LocalDateTime.now()
        }
    }

    private fun createNewProduct(request: ProductTemporalRequestDTO, user: User): Product {
        return productRepository.save(
            Product(
                productPostingStatus = request.postingStatus,
                productTitle = request.productTitle,
                productContent = request.productContent,
                productPrice = request.productPrice,
                creator = user
            )
        )
    }

    /**
     *  상품 수정 서비스 ( 작가, 매니저 )
     *  @param request: ProductUpdateRequestDTO(상품 수정 요청 객체)
     *  @return 수정 실패 유무
     */
    @Transactional
    override fun updateProduct(productId: Long, request: ProductUpdateRequestDTO): Boolean {
        val product = getProduct(productId)
        validationService.updateProductValidate(product, request)

        // 검토완료된 내용을 수정할 경우,
        if ( product.productPostingStatus == ProductPostingStatus.CLEAR_REVIEW && request.postingStatus == ProductPostingStatus.ASK_REVIEW ) {
            val uuid = authenticationFacade.getUsername()
            productRepository.save(
                Product(
                    productPostingStatus = ProductPostingStatus.ASK_REVIEW,
                    productTitle = request.productTitle ?: product.productTitle,
                    productContent =  request.productContent ?: product.productContent,
                    productPrice = request.productPrice ?: product.productPrice,
                    creator = userService.findByUuid(uuid),
                    version = product.version + 1,
                    isActive = false,
                    consentorName = product.consentorName
                )
            )
            translateService.saveTranslationIfNeeded(product, "ko", request.productTitle ?: product.productTitle, request.productContent ?: product.productContent)
        }else {
            if ( product.productPostingStatus == ProductPostingStatus.REJECT_REVIEW ) {
                // 거절 상품의 경우 제목과 본문 변경 시, 번역 재호출
                eventPublisher.publishEvent(TranslateEvent(product.id!!))
            }else {
                product.productTitle = request.productTitle ?: product.productTitle
                request.productContent = request.productContent ?: product.productContent
                product.productPostingStatus = request.postingStatus
                request.productPrice?.let { product.productPrice = it }
                product.updateAt = LocalDateTime.now()
                translateService.saveTranslationIfNeeded(product, "ko", request.productTitle ?: product.productTitle, request.productContent ?: product.productContent)
            }

        }

        return true
    }

    /**
     * 작가 상품 검토 요청 서비스
     * @param productId: Long(상품 일련번호)
     * @param request: ProductSubmitRequestDTO(상품 검토요청 객체)
     * @return Boolean 성공 유무
     */
    @Transactional
    @Throws(CarpenStreetException::class)
    override fun submitProduct(productId: Long, request: ProductSubmitRequestDTO): Boolean {
        val uuid = authenticationFacade.getUsername()
        val product = getProduct(productId)

        if ( product.creator?.uuid != uuid ) {
            throw CarpenStreetException(ErrorMessage.PRODUCT_USER_NOT_MATCH)
        }
        request.productMessage.let { product.message = it }
        product.productPostingStatus = ProductPostingStatus.ASK_REVIEW
        product.updateAt = LocalDateTime.now()

        return true
    }

    /**
     * 상품 검토중 전환 서비스 (매니저 권한 )
     * @param productId: Long
     * @return Boolean 검토 중 전환 성공 여부
     * @exception CarpenStreetException
     */
    @Transactional
    @Throws(CarpenStreetException::class)
    override fun underReviewProduct(productId: Long): Boolean {
        val product = getProduct(productId)
        validationService.underProductFilterByProduct(product)

        val managerUuid = authenticationFacade.getUsername()
        val manager = userService.findByUuid(managerUuid)

        product.productPostingStatus = ProductPostingStatus.UNDER_REVIEW
        product.consentorName = manager.name
        product.updateAt = LocalDateTime.now()

        // 상품 제목과 본문 텍스트 번역
        eventPublisher.publishEvent(TranslateEvent(productId))

        // 실제 동작하지 않기 때문에 주석 처리
        messageService.sendMessageToPartnerByProduct(product, ProductPostingStatus.UNDER_REVIEW)

        return true
    }

    /**
     * 상품 목록 조회 서비스
     * @param pageable: 페이지네이션
     * @return 상품 목록 페이지네이션
     */
    @Transactional
    override fun getProducts(pageable: Pageable): Page<ProductsResponseDTO> {
        val uuid = authenticationFacade.getUsername()
        val user = userService.findByUuid(uuid)
        val filter = validationService.getProductsFilter(user)

        val qProduct = QProduct.product
        val qUser = QUser.user

        val query = queryFactory
            .select(
                Projections.fields(
                    ProductsResponseDTO::class.java,
                    qProduct.id.`as`("productId"),
                    qProduct.productPostingStatus.`as`("productPostingStatus"),
                    qProduct.productTitle.`as`("productTitle"),
                    qProduct.creator.name.`as`("creatorName"),
                )
            )
            .from(qProduct)
            .join(qUser).on(qProduct.creator.eq(qUser))
            .offset(pageable.offset)
            .orderBy(*pageable.sort.toQueryDslOrder(qProduct))
            .where(*filter.toTypedArray())
            .limit(pageable.pageSize.toLong())

        val total = queryFactory
            .select(qProduct.count())
            .from(qProduct)
            .join(qUser).on(qProduct.creator.eq(qUser))
            .fetchOne()

        return PageImpl(query.fetch(), pageable, total ?: 0 )
    }


    @Transactional
    @Throws(CarpenStreetException::class)
    override fun getProduct(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseThrow { throw CarpenStreetException(ErrorMessage.PRODUCT_NOT_EXIST) }
    }


    @Transactional
    @Throws(CarpenStreetException::class)
    override fun approveProduct(productId: Long): Boolean {
        val product = getProduct(productId)

        product.productPostingStatus = ProductPostingStatus.CLEAR_REVIEW
        product.updateAt = LocalDateTime.now()

        messageService.sendMessageToPartnerByProduct(product, ProductPostingStatus.CLEAR_REVIEW)

        return true
    }

    @Transactional
    @Throws(CarpenStreetException::class)
    override fun getProductDetail(productId: Long, locale: String): ProductDetailResponseDTO? {
        val product = getProduct(productId)
        val filter = validationService.getProductDetailFilterByProduct(product)

        val qTranslate = QTranslate.translate
        val qProduct = QProduct.product

        return queryFactory
            .select(
                Projections.fields(
                    ProductDetailResponseDTO::class.java,
                    Expressions.stringTemplate(
                        "CASE WHEN {0} = {1} THEN {2} ELSE {0} END",
                        qProduct.productTitle,
                        qTranslate.translateCode,
                        qTranslate.message
                    ).`as`("productTitle"),
                    Expressions.stringTemplate(
                        "CASE WHEN {0} = {1} THEN {2} ELSE {0} END",
                        qProduct.productContent,
                        qTranslate.translateCode,
                        qTranslate.message
                    ).`as`("productContent"),
                    qProduct.productPrice.`as`("productPrice"),
                    qProduct.creator.name.`as`("creatorName")
                )
            )
            .from(qProduct)
            .leftJoin(qTranslate).on(qTranslate.product.eq(qProduct))
            .where(*filter.toTypedArray(),qTranslate.locale.eq(locale))
            .fetchOne()
    }

    /**
     *  상품 검토 거절 서비스
     *  @param productId: Long(상품 일련번호)
     *  @param request: ProductRejectRequestDTO(상품 거절 요청 객체)
     *  @return 거절 유무
     **/
    @Transactional
    @Throws(CarpenStreetException::class)
    override fun rejectProduct(productId: Long, request: ProductRejectRequestDTO): Boolean {

        val product = getProduct(productId)
        messageService.sendMessageToPartnerByProduct(product, ProductPostingStatus.REJECT_REVIEW)

        product.productPostingStatus = ProductPostingStatus.REJECT_REVIEW
        product.rejectMessage = request.message
        product.updateAt = LocalDateTime.now()

        translateService.deleteTranslate(product)

        return true
    }

}