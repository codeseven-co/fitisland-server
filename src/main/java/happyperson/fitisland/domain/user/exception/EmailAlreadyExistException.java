package happyperson.fitisland.domain.user.exception;

import happyperson.fitisland.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends CustomException {

    private static final String MESSAGE = "이미 회원가입된 사용자입니다.";
    public EmailAlreadyExistException() {
        super(MESSAGE);
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
