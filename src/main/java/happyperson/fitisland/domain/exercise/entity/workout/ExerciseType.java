package happyperson.fitisland.domain.exercise.entity.workout;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type; //앞, 뒤

    @OneToMany(mappedBy = "exerciseType")
    private Set<ExercisePartType> exerciseBodyParts = new HashSet<>();

    @Builder
    public ExerciseType(Long id, String type, Set<ExercisePartType> exerciseBodyParts) {
        this.id = id;
        this.type = type;
        this.exerciseBodyParts = exerciseBodyParts;
    }
}
