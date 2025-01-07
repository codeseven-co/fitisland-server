package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.dto.request.ExerciseGuideCreateRequest;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseGuideCreateResponse;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseGuideDetailResponse;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseGuideListResponse;
import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import happyperson.fitisland.domain.exercise.exception.ExerciseGuideNotFoundException;
import happyperson.fitisland.domain.exercise.exception.ExerciseGuideUnauthorizedDeletionException;
import happyperson.fitisland.domain.exercise.repository.ExerciseGuideRepository;
import happyperson.fitisland.global.security.oauth2.CustomOAuth2User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseGuideService {

    private final ExerciseGuideRepository exerciseGuideRepository;

    /**
     * 운동가이드 조회
     */
    public ExerciseGuideDetailResponse findExerciseGuideDetail(Long guideId, Long userId) {

        ExerciseGuide savedExerciseGuide = exerciseGuideRepository.findById(guideId)
            .orElseThrow(ExerciseGuideNotFoundException::new);

        Boolean isLike = false;

        if (userId != null) { // userId가 있을 경우에만 isLike 여부 확인
            isLike = savedExerciseGuide.getLikes().stream()
                .anyMatch(like -> like.getUser().getId().equals(userId));
        }

        return ExerciseGuideDetailResponse.of(savedExerciseGuide, isLike);
    }

    /**
     * 운동가이드 삭제
     *
     * @param guideId     삭제할 가이드 ID
     * @param userDetails
     * @throws ExerciseGuideNotFoundException 가이드를 찾을 수 없는 경우
     */
    public void deleteExerciseGuide(Long guideId, CustomOAuth2User userDetails) {

        ExerciseGuide exerciseGuide = exerciseGuideRepository.findById(guideId)
            .orElseThrow(ExerciseGuideNotFoundException::new);

        // 작성자와 삭제 요청자 비교
        validateDeletionPermission(exerciseGuide, userDetails);

        exerciseGuideRepository.delete(exerciseGuide);
    }
    /**
     * 운동 가이드 삭제 권한 검증
     *
     * @param exerciseGuide 삭제 대상 운동 가이드
     * @param userDetails   현재 로그인한 사용자 정보
     * @throws ExerciseGuideUnauthorizedDeletionException 삭제 권한이 없는 경우 발생하는 예외
     */
    private void validateDeletionPermission(ExerciseGuide exerciseGuide, CustomOAuth2User userDetails) {
        // 관리자 권한 확인
        if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return; // 관리자는 모든 가이드를 삭제할 수 있음
        }

        // 작성자 여부 확인 (작성자 ID를 어떻게 얻는지에 따라 구현이 달라질 수 있음)
        if (!exerciseGuide.isCreatedBy(1L)) { //변경예정
            throw new ExerciseGuideUnauthorizedDeletionException();
        }
    }

    public ExerciseGuideCreateResponse saveExerciseGuide(ExerciseGuideCreateRequest request, UserDetails userDetails) {
//        exerciseGuideRepository.save()
        return null;
    }

    public List<ExerciseGuideListResponse> getExerciseGuideList(Long userId) {
        List<ExerciseGuide> exerciseGuides = exerciseGuideRepository.findAllWithLikes(); // 페이징 제외

        return exerciseGuides.stream()
            .map(exerciseGuide -> {
                Boolean isLike = (userId != null) ? exerciseGuide.getLikes().stream()
                    .anyMatch(like -> like.getUser().getId().equals(userId)) : null;
                return ExerciseGuideListResponse.of(exerciseGuide, isLike); // of 메서드 사용
            })
            .collect(Collectors.toList());
    }

    /**
     * 운동가이드 수정
     */

    /**
     * 운동가이드 생성
     * @return ExerciseGuide.class
     */

    /**
     * 운동 목록 조회
     */
}
