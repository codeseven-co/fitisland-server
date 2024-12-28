package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import happyperson.fitisland.domain.exercise.entity.Like;
import happyperson.fitisland.domain.exercise.repository.ExerciseGuideRepository;
import happyperson.fitisland.domain.exercise.repository.LikeRepository;
import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ExerciseGuideRepository exerciseGuideRepository;

    public void addLike(Long userId, Long exerciseGuideId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사용자입니다."));

        ExerciseGuide exerciseGuide = exerciseGuideRepository.findById(exerciseGuideId)
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 운동 가이드입니다."));

        if (likeRepository.findByUserIdAndExerciseGuideId(userId, exerciseGuideId).isPresent()) {
            throw new IllegalStateException("이미 좋아요한 운동 가이드입니다.");
        }

        Like like = Like.of(user, exerciseGuide);
        likeRepository.save(like);
    }

    public void deleteLike(Long userId, Long exerciseGuideId) {
        Like like = likeRepository.findByUserIdAndExerciseGuideId(userId, exerciseGuideId)
            .orElseThrow(() -> new EntityNotFoundException("좋아요를 하지 않은 운동 가이드입니다."));

        likeRepository.delete(like);
    }
}
