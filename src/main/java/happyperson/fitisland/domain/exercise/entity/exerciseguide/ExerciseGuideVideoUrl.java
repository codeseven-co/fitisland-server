package happyperson.fitisland.domain.exercise.entity.exerciseguide;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseGuideVideoUrl {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExerciseGuide exerciseGuide;

    private String url;

    private String youtubeTitle;

    private String youtubeOwner;

    public void setExerciseGuide(ExerciseGuide exerciseGuide) {
        this.exerciseGuide = exerciseGuide;
    }
}
