package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.entity.workout.WorkoutRecord;
import happyperson.fitisland.domain.exercise.service.WorkoutService;
import happyperson.fitisland.global.security.oauth2.CustomOAuth2User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    /**
     * WorkoutRecord 일별 목록 조회
     */
    @GetMapping("/daily/{date}")
    public List<WorkoutRecord> getDailyWorkoutRecords(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable LocalDate date) {

        return workoutService.getWorkoutRecordsByDate(user.getId(), date);
    }

}