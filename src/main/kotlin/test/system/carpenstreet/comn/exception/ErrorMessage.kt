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
    ACCESS_DENIED(HttpStatus.NOT_ACCEPTABLE,"SE0002","접근 권한이 없습니다."),

    /** 사용자 관련 오류 **/
    UUID_IS_NOT_NULL(HttpStatus.BAD_REQUEST,"UE0001","사용자 일련번호가 존재하지 않습니다."),

    /** 상품 관련 오류 **/
    UNSUPPORTED_POSTING_STATUS(HttpStatus.BAD_REQUEST,"PE0005","올바른 저장방식이 아닙니다."),
    PRODUCT_PRICE_NOT_NULL(HttpStatus.BAD_REQUEST,"PE0004","상품 가격을 입력해주세요."),
    PRODUCT_CONTENT_NOT_NULL(HttpStatus.BAD_REQUEST,"PE0003","상품 본문을 입력해주세요."),
    PRODUCT_TITLE_NOT_NULL(HttpStatus.BAD_REQUEST,"PE0002","상품 제목을 입력해주세요."),
    PRODUCT_NOT_EXIST(HttpStatus.BAD_REQUEST, "PE0001","상품이 존재하지 않습니다."),

    /** 회원가입 관련 오류 **/
    DUPLICATE_LOGIN_ID(HttpStatus.BAD_REQUEST,"JE0005","이미 사용중인 아이디입니다."),
    LOGIN_ID_REQUIRE_VALUE(HttpStatus.BAD_REQUEST,"JE0004","로그인 아이디는 필수값입니다."),
    UNSUPPORTED_USER_ROLE(HttpStatus.BAD_REQUEST,"JE0003","지원하지 않는 역할입니다."),
    PHONE_REQUIRE_VALUE(HttpStatus.BAD_REQUEST,"JE0002","휴대폰은 필수값입니다."),
    NAME_REQUIRE_VALUE(HttpStatus.BAD_REQUEST,"JE0001","이름은 필수값입니다."),

    /** 토큰 관련 오류 **/
    DECRYPT_TOLE_FAIL(HttpStatus.UNAUTHORIZED,"TE0008","토큰 복호화에 실패하였습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"TE0007","유효하지 않은 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"TE0006","지원하지 않는 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"TE0005","만료된 토큰입니다."),
    INVALID_JWT_FORMAT(HttpStatus.UNAUTHORIZED,"TE0004","유효하지 않은 형식의 접근입니다."),
    INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED,"TE0003","유효하지 않은 서명입니다."),

    /** 로그인 관련 오류 **/
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