package happyperson.fitisland.domain.exercise.entity.workout;

import happyperson.fitisland.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;

    private Integer setNumber;

    private Integer weight;

    private Integer reps;

    private Boolean isCompleted;

    private Double distance; //유산소 거리

    private Integer duration; //초단위로 저장

    @Builder
    private WorkoutSet(Long id, WorkoutExercise workoutExercise, Integer setNumber, Integer weight,
        Integer reps, Boolean isCompleted, Double distance, Integer duration) {
        this.id = id;
        this.workoutExercise = workoutExercise;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
        this.isCompleted = isCompleted;
        this.distance = distance;
        this.duration = duration;
    }
}
