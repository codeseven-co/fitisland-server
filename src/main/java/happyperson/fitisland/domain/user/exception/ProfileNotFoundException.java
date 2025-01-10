package happyperson.fitisland.domain.user.exception;

import happyperson.fitisland.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ProfileNotFoundException extends CustomException {

    private static final String MESSAGE = "사용자의 최근 프로필이 없습니다.";
    public ProfileNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
