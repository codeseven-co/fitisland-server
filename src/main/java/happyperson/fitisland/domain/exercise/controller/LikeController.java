package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.service.LikeService;
import happyperson.fitisland.domain.oauthjwt.dto.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    /**
     * 운동 좋아요, 싫어요
     */
    @PostMapping("/{exerciseGuideId}")
    public ResponseEntity<Void> addLike(
        @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
        @PathVariable Long exerciseGuideId
    ) {
        likeService.addLike(customOAuth2User.getId(), exerciseGuideId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{exerciseGuideId}")
    public ResponseEntity<Void> deleteLike(
        @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
        @PathVariable Long exerciseGuideId
    ) {
        likeService.deleteLike(customOAuth2User.getId(), exerciseGuideId);
        return ResponseEntity.ok().build();
    }
}
