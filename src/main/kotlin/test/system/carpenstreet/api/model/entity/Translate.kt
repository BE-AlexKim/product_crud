package test.system.carpenstreet.api.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import test.system.carpenstreet.api.model.enums.Language
import java.time.LocalDateTime
import java.util.UUID

/**
 *packageName    : test.system.carpenstreet.api.model.entity
 * fileName       : Translate
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@Entity
@Table(name = "tb_translate_info")
@Comment("번역 정보 테이블")
class Translate(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translate_id")
    var id: Long? = null,

    @Column(name = "translate_text")
    var text: String,

    @Column(name = "translate_code")
    var code: String,

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    @Comment("언어값")
    var language: Language,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product,

    @Column(name = "create_at")
    val createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null
) {

    constructor(text:String, language: Language, product: Product): this(
        id = null,
        text = text,
        code = UUID.randomUUID().toString(),
        language = language,
        product = product,
        createAt = LocalDateTime.now(),
        updateAt = null
    )
}