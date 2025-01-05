package happyperson.fitisland.global.exception;

import happyperson.fitisland.global.exception.response.Validation;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 아래 형식으로 상속받아서 커스텀 예외 생성
 * public class DefaultCustomException extends CustomException{
 *     private static final String MESSAGE = "접근이 거부되었습니다";
 *
 *     public CustomExceptionImpl() {
 *         super(MESSAGE);
 *     }
 *
 *     @Override
 *     public HttpStatus getHttpStatus() {
 *         return HttpStatus.FORBIDDEN;
 *     }
 * }
 */
@Getter
public abstract class CustomException extends RuntimeException{

    private final List<Validation> validations;
    public CustomException(String message) {
        super(message);
        validations = new ArrayList<>();
    }
    public CustomException(String message, List<Validation> validations){
        super(message);
        this.validations = validations != null ? validations : new ArrayList<>();
    }

    public abstract HttpStatus getHttpStatus();

}
