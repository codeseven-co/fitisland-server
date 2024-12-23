package happyperson.fitisland.common.exception.api;

import happyperson.fitisland.common.exception.CustomExceptionImpl;
import happyperson.fitisland.common.exception.response.Validation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExceptionApiController {

    @GetMapping("/test/exception/{id}")
    public String test(@PathVariable String id) throws Exception {
        if ("ex".equals(id)){
            throw new RuntimeException("잘못된 사용자");
        }

        if ("bad".equals(id)){
            throw new CustomExceptionImpl();
        }

        if ("validations".equals(id)){
            List<Validation> validationList = new ArrayList<>();
            validationList.add(new Validation("Title","없어요"));
            validationList.add(new Validation("Content","없어요"));
            throw new CustomExceptionImpl(validationList);
        }
        log.info("test success");
        return "test success";
    }

}
