package happyperson.fitisland.domain.exercise.dto.response.exerciseguide;

import happyperson.fitisland.domain.exercise.entity.workout.ExercisePart;
import happyperson.fitisland.domain.exercise.entity.workout.ExerciseType;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

public class ExerciseResponse {

    @Data
    public static class Type {
        private Long id;
        private String type;
        private Set<Part> parts;

        public Type(ExerciseType partType) {
            this.id = partType.getId();
            this.type = partType.getType();
            this.parts = partType.getExerciseBodyParts().stream()
                    .map(bp -> new Part(bp.getExercisePart()))
                    .collect(Collectors.toSet());
        }
    }

    @Data
    public static class Part {
        private Long id;
        private String part;

        public Part(ExercisePart exercisePart) {
            this.id = exercisePart.getId();
            this.part = exercisePart.getPart();
        }
    }
}
