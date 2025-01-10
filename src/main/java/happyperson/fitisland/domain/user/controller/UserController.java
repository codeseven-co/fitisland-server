package happyperson.fitisland.domain.user.controller;

import happyperson.fitisland.domain.user.dto.request.UserRequest;
import happyperson.fitisland.domain.user.dto.response.UserResponse;
import happyperson.fitisland.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userService.findRecentProfile(userDetails.getUsername()));
    }

    @PutMapping("/name")
    public ResponseEntity<Void> updateName(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserRequest.Name dto
    ) {
        userService.updateName(userDetails, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/nickname")
    public ResponseEntity<Void> updateNickname(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserRequest.Nickname dto
    ) {
        userService.updateNickname(userDetails, dto);
        return ResponseEntity.ok().build();
    }
}
