package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.dto.response.workout.WorkoutExerciseListResponse;
import happyperson.fitisland.domain.exercise.repository.WorkoutExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
//    public WorkoutExerciseListResponse getWorkoutExercises(Long exerciseRecordId, Long userId) {
//
//    }
}
