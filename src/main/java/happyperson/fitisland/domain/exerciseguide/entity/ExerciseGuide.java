package happyperson.fitisland.domain.exerciseguide.entity;

import happyperson.fitisland.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
public class ExerciseGuide extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titleKo;
    private String titleEn;
    private String imagePath;
    private String category;
    private String originName;
    private String markdownPath;
    private String tip;
    private String warning;

}
