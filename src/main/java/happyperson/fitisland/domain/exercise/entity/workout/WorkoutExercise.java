package happyperson.fitisland.domain.exercise.entity.workout;

import happyperson.fitisland.common.base.BaseEntity;
import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutExercise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_record_id")
    private WorkoutRecord workoutRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_guide_id")
    private ExerciseGuide exerciseGuide;

    //순서 정보
    private Integer orderNumber;
}
