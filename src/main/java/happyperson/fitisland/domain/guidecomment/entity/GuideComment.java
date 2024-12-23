package happyperson.fitisland.domain.guidecomment.entity;

import happyperson.fitisland.common.base.BaseEntity;
import happyperson.fitisland.domain.exerciseguide.entity.ExerciseGuide;
import happyperson.fitisland.domain.oauthjwt.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuideComment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_guide_id")
    private ExerciseGuide exerciseGuide;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private UserEntity user;  // userId 대신 User 엔티티로 변경
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private GuideComment parentComment; // 부모 댓글
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuideComment> childComments = new ArrayList<>(); // 자식 댓글
    private String comment;
    @Column(name = "likes")  // like는 예약어라서 이름 변경
    private int like;

    // 생성자, getter, setter 등 필요한 메서드 추가
    public GuideComment(ExerciseGuide exerciseGuide, UserEntity user, String comment, GuideComment parentComment) {
        this.exerciseGuide = exerciseGuide;
        this.user = user;
        this.comment = comment;
        this.parentComment = parentComment;
    }

    // 대댓글 추가 메서드
    public void addChildComment(GuideComment child) {
        childComments.add(child);
        child.setParentComment(this);
    }

}
