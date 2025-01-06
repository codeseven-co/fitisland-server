package happyperson.fitisland.domain.user.exception;

import happyperson.fitisland.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NicknameAlreadyExistException extends CustomException {
    private static final String MESSAGE = "이미 사용중인 닉네임입니다.";
    public NicknameAlreadyExistException() {
        super(MESSAGE);
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
