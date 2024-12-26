package happyperson.fitisland.domain.exercise.exception;

import happyperson.fitisland.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExerciseGuideUnauthorizedDeletionException extends CustomException {
    private static final String MESSAGE = "해당 게시물의 삭제 권한이 없습니다.";

    public ExerciseGuideUnauthorizedDeletionException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
