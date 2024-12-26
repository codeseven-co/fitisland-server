package happyperson.fitisland.domain.exercise.entity;

import happyperson.fitisland.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseGuide extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;
    private String titleKo;
    private String titleEn;
    private String imagePath;
    @Enumerated(EnumType.STRING)
    private ExerciseCategory exerciseCategory;
    private String originName;
    private String markdownPath;
    private String tip;
    private String warning;

    public boolean isCreatedBy(Long userId) {
        return this.getCreatedBy().equals(userId);
    }
}
