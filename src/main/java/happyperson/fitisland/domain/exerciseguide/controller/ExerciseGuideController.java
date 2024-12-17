package happyperson.fitisland.domain.exerciseguide.controller;

import happyperson.fitisland.domain.exerciseguide.dto.request.ExerciseGuideCreateRequest;
import happyperson.fitisland.domain.exerciseguide.dto.response.ExerciseGuideCreateResponse;
import happyperson.fitisland.domain.exerciseguide.dto.response.ExerciseGuideDetailResponse;
import happyperson.fitisland.domain.exerciseguide.dto.response.ExerciseGuideUpdateResponse;
import happyperson.fitisland.domain.exerciseguide.service.ExerciseGuideService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/exerciseGuide")
public class ExerciseGuideController {

    private final ExerciseGuideService exerciseGuideService;

    /**
     * 가이드 상세 보기(완료)
     * @param @PathVariable Long guideId
     * @return ExerciseGuideDetailResponse
     */
    @GetMapping("/{guideId}")
    public ExerciseGuideDetailResponse getExerciseGuide(@PathVariable Long guideId) {

        return exerciseGuideService.findExerciseGuideDetail(guideId);
    }

    /**
     * 생성(유저아이디 수정하기)
     */
    @PostMapping
    public ExerciseGuideCreateResponse createExerciseGuide(
        @Valid ExerciseGuideCreateRequest request,
        @AuthenticationPrincipal UserDetails userDetails) {
        return exerciseGuideService.saveExerciseGuide(request,userDetails);
    }

    /**
     * 수정
     */
//    @PatchMapping
//    public ExerciseGuideUpdateResponse updateExerciseGuide() {
//
//    }

    /**
     * 삭제(유저아이디수정하기)
     */
    @DeleteMapping("/{guideId}")
    public ResponseEntity deleteExerciseGuide(@PathVariable Long guideId,
        @AuthenticationPrincipal UserDetails userDetails) {
        exerciseGuideService.deleteExerciseGuide(guideId,userDetails);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * 운동 가이드 목록 보기
     * 중요! 부위 별로 보이는 기능 구현 어떻게할지 생각
     */

}
