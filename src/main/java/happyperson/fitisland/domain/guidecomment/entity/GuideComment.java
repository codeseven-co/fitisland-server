package happyperson.fitisland.domain.guidecomment.entity;

import happyperson.fitisland.common.base.BaseEntity;
import happyperson.fitisland.domain.exerciseguide.entity.ExerciseGuide;
import happyperson.fitisland.domain.oauthjwt.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
public class GuideComment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "exercise_guide_id")
    private ExerciseGuide exerciseGuide;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private UserEntity user;  // userId 대신 User 엔티티로 변경
    private Long parentCommentId;
    private String comment;
    @Column(name = "likes")  // like는 예약어라서 이름 변경
    private int like;

}
