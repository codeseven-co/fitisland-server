package happyperson.fitisland.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    // 프로필 기록 일자
    private LocalDate date;

    // 키
    private Double height;

    // 몸무게
    private Double weight;

    // 체지방률
    private Double bodyFatPercentage;

    // 운동 목표
    @Enumerated(EnumType.STRING)
    private Target target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Builder
    public Profile(
            Long id, Gender gender, LocalDate date,
            Double height, Double weight, Double bodyFatPercentage,
            Target target, User user
    ) {
        this.id = id;
        this.gender = gender;
        this.date = date;
        this.height = height;
        this.weight = weight;
        this.bodyFatPercentage = bodyFatPercentage;
        this.target = target;
        this.user = user;
    }
}
