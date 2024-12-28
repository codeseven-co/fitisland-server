package happyperson.fitisland.domain.exercise.repository;

import happyperson.fitisland.domain.exercise.entity.workout.WorkoutRecord;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord,Long> {
    List<WorkoutRecord> findByCreatedByAndCreatedDateTimeBetween(
        Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
