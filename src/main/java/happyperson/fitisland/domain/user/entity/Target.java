package happyperson.fitisland.domain.user.entity;

import lombok.Getter;

@Getter
public enum Target {
    SLIM_DOWN("체중 감량"),
    MUSCLE_UP("근육 증가");

    private final String description;

    Target(String description) {
        this.description = description;
    }
}
