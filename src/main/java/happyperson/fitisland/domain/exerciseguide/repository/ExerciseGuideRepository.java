package happyperson.fitisland.domain.exerciseguide.repository;

import happyperson.fitisland.domain.exerciseguide.entity.ExerciseGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseGuideRepository extends JpaRepository<ExerciseGuide,Long> {

}
