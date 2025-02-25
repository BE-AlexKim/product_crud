package test.system.carpenstreet.comn.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *packageName    : test.system.carpenstreet.comn.exception.message
 * fileName       : LoginError
 * author         : joy58
 * date           : 2025-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        joy58       최초 생성
 */
enum class ErrorMessage(
    private val status: HttpStatus,
    private val code: String,
    private val message: String
): ExceptionMessageInitializer {

    /** 서버 오류 **/
    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR,"SE0001","서버 오류"),

    /** 로그인 관련 오류 **/
    DECRYPT_TOLE_FAIL(HttpStatus.UNAUTHORIZED,"LE0008","토큰 복호화에 실패하였습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"LE0007","유효하지 않은 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"LE0006","지원하지 않는 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"LE0005","만료된 토큰입니다."),
    INVALID_JWT_FORMAT(HttpStatus.UNAUTHORIZED,"LE0004","유효하지 않은 형식의 접근입니다."),
    INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED,"LE0003","유효하지 않은 서명입니다."),
    USER_NOT_EXIST(HttpStatus.UNAUTHORIZED,"LE0002","사용자가 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED,"LE0001","비밀번호가 일치하지 않습니다.");


    override fun getCode(): String {
        return code
    }

    override fun getError(): String {
        return name
    }

    override fun getMessage(): String {
        return message
    }

    override fun getHttpStatus(): HttpStatus {
        return status
    }

    override fun defaultException(): CarpenStreetException {
        return CarpenStreetException(this)
    }

    override fun setException(cause: Throwable): CarpenStreetException {
        return CarpenStreetException(this, cause)
    }

    override fun setTimestamp(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}