package happyperson.fitisland.domain.exercise.controller;

import happyperson.fitisland.domain.exercise.service.WorkoutRecordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkoutRecordControllerTest {

    @Autowired
    private WorkoutRecordService workoutRecordService;

    @Test
    @DisplayName("getDailyWorkoutRecords test")
    void test1() {
        
    }

}
