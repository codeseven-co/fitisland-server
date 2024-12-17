package happyperson.fitisland.domain.exerciseguide.dto.request;

import happyperson.fitisland.domain.exerciseguide.entity.ExerciseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseGuideCreateRequest(
    @NotBlank(message = "제목(한국어)은 필수 입력값입니다.")
    String titleKo,

    @NotBlank(message = "제목(영어)은 필수 입력값입니다.")
    String titleEn,

    @NotBlank(message = "이미지 경로는 필수 입력값입니다.")
    String imagePath,

    @NotNull(message = "운동 카테고리는 필수 입력값입니다.")
    ExerciseCategory exerciseCategory,

    @NotBlank(message = "원본 파일명은 필수 입력값입니다.")
    String originName,

    @NotBlank(message = "마크다운 파일 경로는 필수 입력값입니다.")
    String markdownPath,

    String tip, // 팁은 선택적으로 입력 가능

    String warning // 주의사항도 선택적으로 입력 가능
) {
}
