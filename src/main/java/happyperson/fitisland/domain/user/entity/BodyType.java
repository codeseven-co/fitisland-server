package happyperson.fitisland.domain.user.entity;

import lombok.Getter;

@Getter
public enum BodyType {
    SKINNY("마른형", 12),
    MUSCULAR("근육형", 9),
    BULKY("근돼형", 15),
    BUFF("초근육형", 6),
    FIT("보통", 15),
    CHUBBY("근육 없고 배나온", 20),
    FAT("비만형",25);

    // 설명
    private final String description;
    // 체지방률
    private final Integer percentageBodyFat;

    BodyType(String description, Integer percentageBodyFat) {
        this.description = description;
        this.percentageBodyFat = percentageBodyFat;
    }
}
