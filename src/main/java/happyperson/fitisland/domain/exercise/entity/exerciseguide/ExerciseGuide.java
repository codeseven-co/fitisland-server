package happyperson.fitisland.domain.exercise.entity.exerciseguide;

import happyperson.fitisland.common.base.BaseEntity;
import happyperson.fitisland.domain.exercise.entity.Like;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseGuide extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;

    private String titleKo;

    private String titleEn;

    private String imagePath;

    private String originName;

    @OneToMany(mappedBy = "exerciseGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseGuideContent> contentList = new ArrayList<>();

    @OneToMany(mappedBy = "exerciseGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseGuideVideoUrl> videoUrlList = new ArrayList<>();

    @OneToMany(mappedBy = "exerciseGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>(); // 좋아요와의 연관관계 추가

    public boolean isCreatedBy(Long userId) {
        return this.getCreatedBy().equals(userId);
    }

    // 연관관계 편의 메소드
    public void addContent(ExerciseGuideContent content) {
        contentList.add(content);
        content.setExerciseGuide(this);
    }

    public void addVideoUrl(ExerciseGuideVideoUrl videoUrl) {
        videoUrlList.add(videoUrl);
        videoUrl.setExerciseGuide(this);
    }

}
