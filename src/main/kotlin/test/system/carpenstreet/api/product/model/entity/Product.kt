package test.system.carpenstreet.api.product.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.Comment
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus
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
    var productPostingStatus: ProductPostingStatus,

    @Column(name = "product_title")
    @Comment("상품 제목")
    var productTitle: String?,

    @Column(name = "product_content", columnDefinition = "TEXT")
    @Comment("상품 본문")
    var productContent: String?,

    @Column(name = "product_pridce")
    @Comment("상품 가격(원화)")
    var productPrice: Int?,

    @Comment("작가 일련번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    val creatorId: User,

    @Column(name = "consentor_name")
    @Comment("검토자 이름")
    val consentorName: String? = null,

    @Column(name = "msg_to_consentor")
    @Comment("작가 메세지")
    val message: String? = null,

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
