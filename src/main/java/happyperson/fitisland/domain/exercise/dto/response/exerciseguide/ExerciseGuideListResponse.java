package happyperson.fitisland.domain.exercise.dto.response.exerciseguide;

import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import lombok.Builder;

@Builder
public record ExerciseGuideListResponse(  Long id,
                                          String titleKo,
                                          String titleEn,
                                          String imagePath,
                                          Boolean isLike
//                                          List<ExercisePart> exerciseParts // 운동 부위 정보 추가
                                          ) {

    public static ExerciseGuideListResponse of(ExerciseGuide exerciseGuide, Boolean isLike) {
        return ExerciseGuideListResponse.builder()
            .id(exerciseGuide.getId())
            .titleKo(exerciseGuide.getTitleKo())
            .titleEn(exerciseGuide.getTitleEn())
            .imagePath(exerciseGuide.getImagePath())
            .isLike(isLike)
//            .exerciseParts(exerciseGuide.getExerciseParts().stream()
//                .map(exerciseGuideExercisePart -> exerciseGuideExercisePart.getExercisePart())
//                .collect(Collectors.toList()))
            .build();
    }

}
