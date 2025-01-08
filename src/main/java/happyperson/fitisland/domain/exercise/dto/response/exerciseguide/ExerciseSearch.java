package happyperson.fitisland.domain.exercise.dto.response.exerciseguide;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ExerciseSearch {
    // exerciseType Id List
    List<Long> typeIds = new ArrayList<>();
    // exercisePart Id List
    List<Long> partIds = new ArrayList<>();
}
