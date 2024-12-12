package happyperson.fitisland.common.exception;

import happyperson.fitisland.common.exception.response.Validation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class CustomExceptionImpl extends CustomException{
    private static final String MESSAGE = "접근이 거부되었습니다";

    public CustomExceptionImpl() {
        super(MESSAGE);
    }

    public CustomExceptionImpl(List<Validation> validations){
        super(MESSAGE, validations);
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
