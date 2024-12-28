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
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExercisePart {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String part;

    // Exercise와의 관계는 ExerciseBodyPart를 통해 설정
    @OneToMany(mappedBy = "bodyPart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExerciseGuidePart> exerciseBodyParts = new HashSet<>();

}
