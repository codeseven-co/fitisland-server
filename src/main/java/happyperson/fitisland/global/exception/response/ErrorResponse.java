package happyperson.fitisland.global.exception.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

/**
 * String code : HttpStatus.value, String type : HttpStatus.reasonPhrase, String message : detail
 * message Validation validation : "validation" : { "key" : "value" }
 */
public record ErrorResponse(String code, String type, String message, List<Validation> validations) {

    @Builder
    public ErrorResponse(String code, String type, String message, List<Validation> validations) {
        this.code = code;
        this.type = type;
        this.message = message;
        this.validations = validations != null ? validations : new ArrayList<>();
    }

}
