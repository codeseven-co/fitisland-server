package happyperson.fitisland.domain.exercise.repository;

import happyperson.fitisland.domain.exercise.entity.workout.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {
}
