package happyperson.fitisland.domain.exercise.dto.response.exerciseguide;


import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record ExerciseGuideDetailResponse(
    Long id,
    String titleKo,
    String titleEn,
    String imagePath,
    String originName,
    List<ExerciseGuideContentDto> contents,
    List<ExerciseGuideVideoUrlDto> videoUrls,
    Boolean isLike
) {

    public static ExerciseGuideDetailResponse of(ExerciseGuide exerciseGuide, Boolean isLike) {
        return ExerciseGuideDetailResponse.builder()
            .id(exerciseGuide.getId())
            .titleKo(exerciseGuide.getTitleKo())
            .titleEn(exerciseGuide.getTitleEn())
            .imagePath(exerciseGuide.getImagePath())
            .originName(exerciseGuide.getOriginName())
            .contents(exerciseGuide.getContentList().stream()
                .map(ExerciseGuideContentDto::of)
                .collect(Collectors.toList()))
            .videoUrls(exerciseGuide.getVideoUrlList().stream()
                .map(ExerciseGuideVideoUrlDto::of)
                .collect(Collectors.toList()))
            .isLike(isLike)
            .build();
    }
}
