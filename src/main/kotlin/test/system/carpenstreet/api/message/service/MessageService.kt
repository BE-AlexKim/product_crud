package test.system.carpenstreet.api.message.service

import test.system.carpenstreet.api.product.model.entity.Product
import test.system.carpenstreet.api.product.model.enums.ProductPostingStatus

/**
 *packageName    : test.system.carpenstreet.api.message.service
 * fileName       : MessageService
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
interface MessageService {

    fun sendMessageToPartnerByProduct(product: Product, postingStatus: ProductPostingStatus)

}