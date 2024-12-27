package happyperson.fitisland.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 성별
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 키
    private Integer height;

    // 몸무게
    private Integer weight;

    // 체지방률
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    // 운동 목표
    @Enumerated(EnumType.STRING)
    private Target target;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Builder
    public Profile(Long id, Gender gender, Integer height, Integer weight, BodyType bodyType, Target target, User user) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bodyType = bodyType;
        this.target = target;
        this.user = user;
    }
}
