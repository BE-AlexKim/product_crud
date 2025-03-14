package test.system.carpenstreet.api.product.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.Comment
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
import test.system.carpenstreet.api.translate.model.entity.Translate
import test.system.carpenstreet.api.user.model.entity.User
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
    var productPostingStatus: ProductPostingStatus = ProductPostingStatus.TEMPORARY,

    @Column(name = "product_title")
    @Comment("상품 제목")
    var productTitle: String? = null,

    @Column(name = "product_content", columnDefinition = "TEXT")
    @Comment("상품 본문")
    var productContent: String? = null,

    @Column(name = "product_pridce")
    @Comment("상품 가격(원화)")
    var productPrice: Int? = null,

    @Comment("작가 일련번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val creator: User? = null,

    @Comment("상품 버전")
    @Column(name = "product_version")
    val version: Int = 1,

    @Comment("상품 활성화 여부")
    @Column(name = "is_active")
    val isActive: Boolean = false,

    @Column(name = "consentor_name")
    @Comment("검토자 이름")
    var consentorName: String? = null,

    @Column(name = "ask_message")
    @Comment("작가 메세지")
    var message: String? = null,

    @Column(name = "reject_message")
    @Comment("거절 사유")
    var rejectMessage: String? = null,

    @Column(name = "create_at")
    @Comment("최초 생성일시")
    val creatAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_at")
    @Comment("최종 수정일시")
    var updateAt: LocalDateTime? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Product

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , productPostingStatus = $productPostingStatus , productTitle = $productTitle , productContent = $productContent , productPrice = $productPrice , consentorName = $consentorName , creatAt = $creatAt , updateAt = $updateAt )"
    }

}
