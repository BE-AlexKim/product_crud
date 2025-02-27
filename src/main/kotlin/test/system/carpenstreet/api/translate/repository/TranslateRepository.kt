package test.system.carpenstreet.api.translate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.translate.model.entity.Translate

/**
 *packageName    : test.system.carpenstreet.api.translate.repository
 * fileName       : TranslateRepository
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
@Repository
interface TranslateRepository: JpaRepository<Translate, Long> {

    fun findByTranslateCodeAndLocale(translateCode: String, locale: String): Translate?

    fun findByProduct(product: Product): List<Translate>

}