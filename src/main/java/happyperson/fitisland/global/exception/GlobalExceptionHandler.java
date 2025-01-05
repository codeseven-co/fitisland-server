package happyperson.fitisland.global.exception;

import happyperson.fitisland.global.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 500에러 처리
     * 최상단 에러 처리
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse globalExHandle(Exception e) {

        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse = ErrorResponse.builder()
            .message(e.getMessage())
            .code(String.valueOf(httpStatus.value()))
            .type(httpStatus.getReasonPhrase())
            .build();

        return errorResponse;
    }

    /**
     * 커스텀 에러 처리
     * ex)
     * CustomException(HttpStatus.BAD_REQUEST,"잘못된 입력입니다")
     * CustomException 을 사용해서 ErrorResult 생성
     * 상태추 추가 후 Status 변경해서 반환
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CustomException e) {

        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());

        /**
         * CustomException 필드 이용
         * private HttpStatus httpStatus;
         */
        HttpStatus httpStatus = e.getHttpStatus();

        ErrorResponse errorResponse = ErrorResponse.builder()
            .message(e.getMessage())
            .code(String.valueOf(httpStatus.value()))
            .type(httpStatus.getReasonPhrase())
            .validations(e.getValidations())
            .build();

        return ResponseEntity.status(httpStatus.value()).body(errorResponse);
    }

}
