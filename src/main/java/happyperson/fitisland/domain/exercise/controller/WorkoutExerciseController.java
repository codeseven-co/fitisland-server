package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.dto.response.workout.WorkoutExerciseListResponse;
import happyperson.fitisland.domain.exercise.service.WorkoutExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workout-exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    //운동레크드에 기록된 운동 종목들 찾기
//    @GetMapping("/{ExerciseRecordId}")
//    public WorkoutExerciseListResponse getWorkoutExerciseList(@PathVariable("ExerciseRecordId") Long exerciseRecordId) {
//        Long userId = 1L;
//        return workoutExerciseService.getWorkoutExercises(exerciseRecordId, userId);
//    }
}
