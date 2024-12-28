package happyperson.fitisland.domain.exercise.entity.workout;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type; //앞, 뒤
}
