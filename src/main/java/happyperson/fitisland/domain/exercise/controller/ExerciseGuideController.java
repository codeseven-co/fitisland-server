package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseGuideDetailResponse;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseGuideListResponse;
import happyperson.fitisland.domain.exercise.service.ExerciseGuideService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercise-guides")
public class ExerciseGuideController {

    private final ExerciseGuideService exerciseGuideService;

    /**
     * 가이드 상세 보기(완료)
     * @param @PathVariable Long guideId
     * @return ExerciseGuideDetailResponse
     */
    @GetMapping("/{guideId}")
    public ResponseEntity<ExerciseGuideDetailResponse> getExerciseGuide(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable Long guideId
    ) {
        return ResponseEntity.ok(exerciseGuideService.findExerciseGuideDetail(userDetails, guideId));
    }

    /**
     * 운동 가이드 목록 보기 검색기능
     */
    @GetMapping
    public List<ExerciseGuideListResponse> getExerciseGuideList(
        @RequestParam(value = "userId", required = false) Long userId // required = false 설정
    ) {
        return exerciseGuideService.getExerciseGuideList(userId);
    }

    /**
     * 생성(유저아이디 수정하기)
     */
    //수정

    /**
     * 삭제(유저아이디수정하기)
     */


}
