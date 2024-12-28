package happyperson.fitisland.domain.exercise.entity;

import happyperson.fitisland.common.base.BaseEntity;
import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import happyperson.fitisland.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Likes")
public class Like extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_guide_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ExerciseGuide exerciseGuide;

    @Builder
    private Like(User user, ExerciseGuide exerciseGuide) {
        this.user = user;
        this.exerciseGuide = exerciseGuide;
    }

    public static Like of(User user,ExerciseGuide exerciseGuide){
        return Like.builder()
            .user(user)
            .exerciseGuide(exerciseGuide)
            .build();
    }

}
