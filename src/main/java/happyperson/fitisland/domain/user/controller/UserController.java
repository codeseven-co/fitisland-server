package happyperson.fitisland.domain.user.controller;

import happyperson.fitisland.domain.user.dto.response.UserResponse;
import happyperson.fitisland.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/account")
    public ResponseEntity<UserResponse.AccountDto> findAccount(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.findAccount(userDetails.getUsername()));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse.ProfileDto> findProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.findProfile(userDetails.getUsername()));
    }
}
