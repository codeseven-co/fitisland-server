package happyperson.fitisland.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MyController {

    @GetMapping("/my")
    @ResponseBody
    public String myAPI(@CookieValue(value = "Authorization", required = false) String authToken) {
        if (authToken != null) {
            // 토큰을 사용하여 인증 처리
            log.info("Authorization 쿠키에서 토큰 가져옴: {}", authToken);
            return "Token received from cookie";
        }
        log.info("로그인 성공");
        return "my route";
    }
}
