package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.entity.workout.WorkoutRecord;
import happyperson.fitisland.domain.exercise.repository.WorkoutRecordRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutRecordService {

    private final WorkoutRecordRepository workoutRecordRepository;


    public List<WorkoutRecord> getWorkoutRecordsByDate(Long userId, LocalDate date) {
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN); // 해당 날짜의 00:00:00
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX);   // 해당 날짜의 23:59:59
        return workoutRecordRepository.findByCreatedByAndCreatedDateTimeBetween(userId, startOfDay, endOfDay);
    }
}
