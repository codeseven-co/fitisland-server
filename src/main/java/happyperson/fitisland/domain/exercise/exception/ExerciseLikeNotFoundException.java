package happyperson.fitisland.domain.exercise.exception;

import happyperson.fitisland.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExerciseLikeNotFoundException extends CustomException {

    private static final String MESSAGE = "좋아요를 하지 않은 운동 가이드입니다.";
    public ExerciseLikeNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
