package happyperson.fitisland.domain.exercise.exception;

import happyperson.fitisland.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyLikeException extends CustomException {
    private static final String MESSAGE = "이미 좋아요한 운동 가이드입니다.";
    public AlreadyLikeException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
