package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.dto.request.ExerciseGuideCreateRequest;
import happyperson.fitisland.domain.exercise.dto.response.ExerciseGuideCreateResponse;
import happyperson.fitisland.domain.exercise.dto.response.ExerciseGuideDetailResponse;
import happyperson.fitisland.domain.exercise.entity.ExerciseGuide;
import happyperson.fitisland.domain.exercise.exception.ExerciseGuideNotFoundException;
import happyperson.fitisland.domain.exercise.exception.ExerciseGuideUnauthorizedDeletionException;
import happyperson.fitisland.domain.exercise.repository.ExerciseGuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExerciseGuideService {

    private ExerciseGuideRepository exerciseGuideRepository;

    /**
     * 운동가이드 조회
     */
    public ExerciseGuideDetailResponse findExerciseGuideDetail(Long guideId) {

        ExerciseGuide savedExerciseGuide = exerciseGuideRepository.findById(guideId)
            .orElseThrow(ExerciseGuideNotFoundException::new);

        return ExerciseGuideDetailResponse.of(savedExerciseGuide);
    }

    /**
     * 운동가이드 삭제
     *
     * @param guideId     삭제할 가이드 ID
     * @param userDetails
     * @throws ExerciseGuideNotFoundException 가이드를 찾을 수 없는 경우
     */
    public void deleteExerciseGuide(Long guideId, UserDetails userDetails) {

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
    private void validateDeletionPermission(ExerciseGuide exerciseGuide, UserDetails userDetails) {
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
