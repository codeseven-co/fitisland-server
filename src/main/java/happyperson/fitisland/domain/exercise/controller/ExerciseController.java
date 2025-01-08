package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseGuideDetailResponse;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseResponse;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseSearch;
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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    private final ExerciseGuideService exerciseGuideService;

    /**
     * 가이드 상세 보기(완료)
     * @param @PathVariable Long exerciseId
     * @return ExerciseGuideDetailResponse
     */
    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseGuideDetailResponse> getExerciseDetail(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable("exerciseId") Long exerciseId
    ) {
        return ResponseEntity.ok(exerciseGuideService.findExerciseGuideDetail(userDetails, exerciseId));
    }

    /**
     * 운동 가이드 목록 보기 검색기능
     */
    @GetMapping
    public ResponseEntity<List<ExerciseResponse.Detail>> getExercisesBySearch(
            @AuthenticationPrincipal UserDetails userDetails,
            ExerciseSearch search
    ) {
        return ResponseEntity.ok(exerciseGuideService.getExercisesBySearch(userDetails, search));
    }

    @GetMapping("/category")
    public ResponseEntity<List<ExerciseResponse.Type>> getExerciseCategory() {
        return ResponseEntity.ok(exerciseGuideService.getExerciseCategory());
    }

    /**
     * 생성(유저아이디 수정하기)
     */
    //수정

    /**
     * 삭제(유저아이디수정하기)
     */


}
