package happyperson.fitisland.domain.user.entity;

import lombok.Getter;

@Getter
public enum Gender {
    M("남자"),
    W("여자");

    // 설명
    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
