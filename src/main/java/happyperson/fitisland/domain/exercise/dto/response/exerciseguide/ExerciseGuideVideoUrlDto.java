package happyperson.fitisland.domain.exercise.dto.response.exerciseguide;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuideVideoUrl;
import lombok.Builder;

@Builder
public record ExerciseGuideVideoUrlDto(Long id,
                                       String url,
                                       String youtubeTitle,
                                       String youtubeOwner) {

    public static ExerciseGuideVideoUrlDto of(ExerciseGuideVideoUrl exerciseGuideVideoUrl) {
        return ExerciseGuideVideoUrlDto.builder()
            .id(exerciseGuideVideoUrl.getId())
            .url(exerciseGuideVideoUrl.getUrl())
            .youtubeTitle(exerciseGuideVideoUrl.getYoutubeTitle())
            .youtubeOwner(exerciseGuideVideoUrl.getYoutubeOwner())
            .build();
    }
}
