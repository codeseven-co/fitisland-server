package happyperson.fitisland.domain.exercise.dto.response.exerciseguide;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuideContent;
import happyperson.fitisland.domain.exercise.entity.enums.ExerciseGuideCategory;
import lombok.Builder;

@Builder
public record ExerciseGuideContentDto(Long id,
                                      ExerciseGuideCategory exerciseGuideCategory,
                                      String content) {

    public static ExerciseGuideContentDto of(ExerciseGuideContent exerciseGuideContent) {
        return ExerciseGuideContentDto.builder()
            .id(exerciseGuideContent.getId())
            .exerciseGuideCategory(exerciseGuideContent.getExerciseGuideCategory())
            .content(exerciseGuideContent.getContent())
            .build();
    }
}
