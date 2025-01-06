package happyperson.fitisland.domain.user.controller;

import happyperson.fitisland.domain.user.dto.request.JoinRequest;
import happyperson.fitisland.domain.user.dto.request.LoginRequest;
import happyperson.fitisland.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("home");
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest request) {
        userService.join(request);
        return ResponseEntity.ok().build();
    }
}
