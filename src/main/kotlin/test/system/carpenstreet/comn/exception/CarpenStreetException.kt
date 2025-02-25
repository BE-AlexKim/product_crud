package test.system.carpenstreet.comn.exception

/**
 *packageName    : test.system.carpenstreet.comn.exception
 * fileName       : LoginException
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
class CarpenStreetException: DefaultExceptionResolver {

    constructor(message: String): super(message)

    constructor(message: String, cause: Throwable): super(message, cause)

    constructor(exceptionCode: ExceptionMessageInitializer): super(exceptionCode)

    constructor(exceptionCode: ExceptionMessageInitializer, cause: Throwable): super(exceptionCode, cause)

}