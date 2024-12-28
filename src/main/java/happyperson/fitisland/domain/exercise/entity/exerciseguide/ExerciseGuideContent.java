package happyperson.fitisland.domain.exercise.entity.exerciseguide;

import happyperson.fitisland.domain.exercise.entity.enums.ExerciseGuideCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseGuideContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_guide_id")
    private ExerciseGuide exerciseGuide;

    @Enumerated(EnumType.STRING)
    private ExerciseGuideCategory exerciseGuideCategory;

    private String content;

    public void setExerciseGuide(ExerciseGuide exerciseGuide) {
        this.exerciseGuide = exerciseGuide;
    }
}
