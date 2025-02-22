package test.system.carpenstreet.api.translate.model.entity

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.Locale

/**
 *packageName    : test.system.carpenstreet.api.translate.model.entity
 * fileName       : Translate
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Entity
@Table(name = "tb_translate_info")
@Comment("번역")
data class Translate(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translate_id")
    @Comment("번역 일련번호")
    val id: Long? = null,

    @Column(name = "translate_code", nullable = false)
    @Comment("번역 코드 값")
    val translateCode: String,

    @Column(name = "translate_locale", nullable = false, length = 10)
    @Comment("지원 언어")
    val locale: String,

    @Column(name = "translate_message", columnDefinition = "TEXT", nullable = false)
    @Comment("번역 텍스트")
    val message: String,

    @Column(name = "create_at")
    @Comment("최초 생성일시")
    val createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "update_at")
    @Comment("최종 수정일시")
    val updateAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Translate

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , translateCode = $translateCode , locale = $locale , message = $message , createAt = $createAt , updateAt = $updateAt )"
    }
}
