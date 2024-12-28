package happyperson.fitisland.domain.exercise.entity.workout;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ExerciseGuideBodyPart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_guide_id")
    private ExerciseGuide exercise;

    @ManyToOne
    @JoinColumn(name = "body_part_id")
    private BodyPart bodyPart;

}
