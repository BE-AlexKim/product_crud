package test.system.carpenstreet.api.product.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test.system.carpenstreet.api.product.model.entity.Product

/**
 *packageName    : test.system.carpenstreet.api.product.repository
 * fileName       : ProductRepository
 * author         : joy58
 * date           : 2025-02-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        joy58       최초 생성
 */
@Repository
interface ProductRepository: JpaRepository<Product, Long> {
}