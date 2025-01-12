package happyperson.fitisland.domain.user.entity;

import happyperson.fitisland.domain.exercise.entity.workout.WorkoutExercise;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String provider;

    @NotNull
    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Social(Long id, String provider, String providerId, User user) {
        this.id = id;
        this.provider = provider;
        this.providerId = providerId;
        this.user = user;
    }
}
