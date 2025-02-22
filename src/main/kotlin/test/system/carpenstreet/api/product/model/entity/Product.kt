package test.system.carpenstreet.api.product.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import java.time.LocalDateTime

/**
 *packageName    : test.system.carpenstreet.api.product.model.entity
 * fileName       : Product
 * author         : joy58
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        joy58       최초 생성
 */
@Entity
@Table(name = "tb_product_info")
@Comment("상품")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Comment("상품 일련번호")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "product_posting_status")
    @Comment("상품 게시 상태")
    val productPostingStatus: ProductPostingStatus,

    @Column(name = "product_title")
    @Comment("상품 제목")
    val productTitle: String,

    @Column(name = "product_content")
    @Comment("상품 본문")
    val productContent: String,

    @Column(name = "product_pridce")
    @Comment("상품 가격(원화)")
    val productPrice: Int,

    @Column(name = "creator_id")
    @Comment("작가 일련번호")
    val creatorId: Long,

    @Column(name = "consent_id")
    @Comment("검토자 일련번호")
    val consentId: Long? = null,

    @Column(name = "create_at")
    @Comment("최초 생성일시")
    val creatAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_at")
    @Comment("최종 수정일시")
    val updateAt: LocalDateTime? = null

)
