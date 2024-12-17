package happyperson.fitisland.domain.exerciseguide.dto.response;


import happyperson.fitisland.domain.exerciseguide.entity.ExerciseCategory;
import happyperson.fitisland.domain.exerciseguide.entity.ExerciseGuide;
import lombok.Builder;

@Builder
public record ExerciseGuideDetailResponse(
    Long id,
    String titleKo,
    String titleEn,
    String imagePath,
//    String videoPath,
    ExerciseCategory exerciseCategory,
    String originName,
    String markdownPath,
    String tip,
    String warning
) {

    public static ExerciseGuideDetailResponse of(ExerciseGuide exerciseGuide) {
        return ExerciseGuideDetailResponse.builder()
            .id(exerciseGuide.getId())
            .titleKo(exerciseGuide.getTitleKo())
            .titleEn(exerciseGuide.getTitleEn())
            .imagePath(exerciseGuide.getImagePath())
            .exerciseCategory(exerciseGuide.getExerciseCategory())
            .originName(exerciseGuide.getOriginName())
            .markdownPath(exerciseGuide.getMarkdownPath())
            .tip(exerciseGuide.getTip())
            .warning(exerciseGuide.getWarning())
            .build();
    }
}
