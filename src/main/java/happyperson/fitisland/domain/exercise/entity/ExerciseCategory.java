package happyperson.fitisland.domain.exercise.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseCategory {
    // 앞면
    CHEST("가슴", "앞면"),
    ABS("복근", "앞면"),
    QUAD("앞허벅지", "앞면"),
    BICEPS("이두", "앞면"),

    // 뒷면
    BACK("등", "뒷면"),
    GLUTES("엉덩이", "뒷면"),
    HAMSTRING("뒷허벅지", "뒷면"),
    CALVES("종아리", "뒷면"),

    // 기타
    SHOULDER("어깨", "기타");

    private final String name;    // final 필드
    private final String part;   // final 필드

}
