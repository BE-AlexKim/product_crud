package test.system.carpenstreet.api.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import test.system.carpenstreet.api.model.enums.ProductStatus
import java.time.LocalDateTime

/**
 *packageName    : test.system.carpenstreet.api.model.entity
 * fileName       : Product
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@Entity
@Table(name = "tb_product_info")
class Product (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    var id: Long? = null,

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    @Comment("상품 검토상태")
    var status: ProductStatus,

    @Column(name = "product_title")
    @Comment("상품 제목")
    var title: String,

    @Column(name = "product_content")
    @Comment("상품 본문내용")
    var content: String,

    @Column(name = "product_price")
    @Comment("상품 가격")
    var price: Long,

    @Column(name = "product_version")
    @Comment("상품 버전")
    var version: Int,

    @Column(name = "is_active")
    @Comment("상품 활성화 여부")
    var isActive: Boolean,

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    var translations: List<Translate> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @Column(name = "create_at")
    val createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_at")
    var updateAt: LocalDateTime?

){
    // 기본 생성자
    constructor() : this(
        id = null,
        status = ProductStatus.TEMPORARY,
        title = "",
        content = "",
        price = 0L,
        version = 1,
        isActive = false,
        createAt = LocalDateTime.now(),
        updateAt = null
    )

    constructor(id: Long, title: String, content: String, price: Long, status: ProductStatus, isActive: Boolean, version: Int, user: User?): this(
        id = id,
        title = title,
        content = content,
        price = price,
        status = status,
        version = version,
        isActive = isActive,
        user = user,
        createAt = LocalDateTime.now(),
        updateAt = null
    )

    constructor(title: String, content: String, price: Long, user: User?): this (
        id = null,
        status = ProductStatus.TEMPORARY,
        title = title,
        content = content,
        price = price,
        version = 1,
        isActive = false,
        user = user,
        createAt = LocalDateTime.now(),
        updateAt = null
    )

    // 기존 상품을 수정할 경우
    constructor(status: ProductStatus, title: String, content: String, price: Long, version: Int, user: User?): this (
        id = null,
        status = status,
        title = title,
        content = content,
        price = price,
        version = version,
        isActive = false,
        user = user,
        createAt = LocalDateTime.now(),
        updateAt = null
    )

    constructor(user: User): this (
        id = null,
        status = ProductStatus.TEMPORARY,
        title = "",
        content = "",
        price = 0L,
        version = 1,
        isActive = false,
        user = user,
        createAt = LocalDateTime.now(),
        updateAt = null
    )

}