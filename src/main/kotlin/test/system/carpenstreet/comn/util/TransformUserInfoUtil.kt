package test.system.carpenstreet.comn.util

import io.github.oshai.kotlinlogging.KotlinLogging
import test.system.carpenstreet.comn.exception.CarpenStreetException
import test.system.carpenstreet.comn.exception.ErrorMessage

/**
 *packageName    : test.system.carpenstreet.comn.util
 * fileName       : UserTranfromInfoUtil
 * author         : joy58
 * date           : 2025-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        joy58       최초 생성
 */
object TransformUserInfoUtil {

    private val log = KotlinLogging.logger {}

    fun transformPhoneNumber(phoneNumber: String?): String? {
        val regex = Regex("""(\d{3})(\d{4})(\d{4})""")
        return phoneNumber?.replaceFirst("^010", "82+ 10")?.replace(regex, "82+ $1-$2-$3")
    }

}