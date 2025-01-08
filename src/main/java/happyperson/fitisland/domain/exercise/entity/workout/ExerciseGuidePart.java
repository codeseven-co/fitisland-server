package happyperson.fitisland.domain.exercise.entity.workout;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseGuidePart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_guide_id")
    private ExerciseGuide exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_part_id")
    private ExercisePart exercisePart;
}
