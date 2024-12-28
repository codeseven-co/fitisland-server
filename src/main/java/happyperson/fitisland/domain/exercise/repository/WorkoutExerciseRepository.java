package happyperson.fitisland.domain.exercise.repository;

import happyperson.fitisland.domain.exercise.entity.workout.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise,Long> {

}
