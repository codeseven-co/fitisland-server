package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.entity.workout.WorkoutRecord;
import happyperson.fitisland.domain.exercise.service.WorkoutRecordService;
import happyperson.fitisland.domain.oauthjwt.dto.CustomOAuth2User;
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
@RequestMapping("/api/v1/workout-records")
public class WorkoutRecordController {

    private final WorkoutRecordService workoutRecordService;

    /**
     * WorkoutRecord 일별 목록 조회
     */
    @GetMapping("/daily/{date}")
    public List<WorkoutRecord> getDailyWorkoutRecords(
        @AuthenticationPrincipal CustomOAuth2User user,
        @PathVariable LocalDate date) {

        return workoutRecordService.getWorkoutRecordsByDate(user.getId(), date);
    }

}
