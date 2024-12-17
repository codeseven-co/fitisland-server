package happyperson.fitisland.domain.oauthjwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MyController {

    @GetMapping("/my")
    @ResponseBody
    public String myAPI(@AuthenticationPrincipal UserDetails userDetails) {
        log.info(userDetails.getUsername());
        return "my route";
    }
}
