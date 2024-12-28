package happyperson.fitisland.domain.exercise.repository;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseGuideRepository extends JpaRepository<ExerciseGuide,Long> {

}
