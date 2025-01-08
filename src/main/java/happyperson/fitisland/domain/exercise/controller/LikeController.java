package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{exerciseGuideId}")
    public ResponseEntity<Void> addLike(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable Long exerciseGuideId
    ) {
        likeService.addLike(userDetails, exerciseGuideId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{exerciseGuideId}")
    public ResponseEntity<Void> deleteLike(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable Long exerciseGuideId
    ) {
        likeService.deleteLike(userDetails, exerciseGuideId);
        return ResponseEntity.ok().build();
    }
}
