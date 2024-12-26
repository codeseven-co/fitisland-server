package happyperson.fitisland.domain.exercise.repository;

import happyperson.fitisland.domain.exercise.entity.Like;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByUserIdAndExerciseGuideId(Long userId, Long exerciseGuideId);
}