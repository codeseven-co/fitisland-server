package happyperson.fitisland.domain.exercise.entity.workout;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExercisePart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String part;

    // Exercise와의 관계는 ExerciseBodyPart를 통해 설정
    @OneToMany(mappedBy = "exercisePart")
    private Set<ExerciseGuidePart> exerciseBodyParts = new HashSet<>();

    @Builder
    public ExercisePart(Long id, String part, Set<ExerciseGuidePart> exerciseBodyParts) {
        this.id = id;
        this.part = part;
        this.exerciseBodyParts = exerciseBodyParts;
    }
}
