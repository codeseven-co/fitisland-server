package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import happyperson.fitisland.domain.exercise.entity.Like;
import happyperson.fitisland.domain.exercise.exception.AlreadyLikeException;
import happyperson.fitisland.domain.exercise.exception.ExerciseGuideNotFoundException;
import happyperson.fitisland.domain.exercise.exception.ExerciseLikeNotFoundException;
import happyperson.fitisland.domain.exercise.repository.ExerciseGuideRepository;
import happyperson.fitisland.domain.exercise.repository.LikeRepository;
import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.exception.UserNotFoundException;
import happyperson.fitisland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ExerciseGuideRepository exerciseGuideRepository;

    public void addLike(UserDetails userDetails, Long exerciseGuideId) {
        User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(UserNotFoundException::new);

        ExerciseGuide exerciseGuide = exerciseGuideRepository.findById(exerciseGuideId)
            .orElseThrow(ExerciseGuideNotFoundException::new);

        if (likeRepository.findByUserIdAndExerciseGuideId(user.getId(), exerciseGuideId).isPresent()) {
            throw new AlreadyLikeException();
        }

        Like like = Like.of(user, exerciseGuide);
        likeRepository.save(like);
    }

    public void deleteLike(UserDetails userDetails, Long exerciseGuideId) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(UserNotFoundException::new);

        Like like = likeRepository.findByUserIdAndExerciseGuideId(user.getId(), exerciseGuideId)
            .orElseThrow(ExerciseLikeNotFoundException::new);

        likeRepository.delete(like);
    }
}
