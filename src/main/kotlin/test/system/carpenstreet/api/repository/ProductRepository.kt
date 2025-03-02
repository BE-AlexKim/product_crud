package test.system.carpenstreet.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test.system.carpenstreet.api.model.entity.Product

/**
 *packageName    : test.system.carpenstreet.api.repository
 * fileName       : ProductRepository
 * author         : joy58
 * date           : 2025-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-28        joy58       최초 생성
 */
@Repository
interface ProductRepository: JpaRepository<Product, Long> {
}