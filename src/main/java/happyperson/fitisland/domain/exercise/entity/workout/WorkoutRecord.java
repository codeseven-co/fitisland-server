package happyperson.fitisland.domain.exercise.entity.workout;

import happyperson.fitisland.common.base.BaseEntity;
import happyperson.fitisland.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer totalDurationSeconds;  // 초 단위로 저장
    @Lob
    private String memo;

    @Builder
    private WorkoutRecord(Long id, User user, LocalDateTime startTime, LocalDateTime endTime,
        Integer totalDurationSeconds, String memo) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalDurationSeconds = totalDurationSeconds;
        this.memo = memo;
    }

    public String getFormattedDuration() {
        int hours = totalDurationSeconds / 3600;
        int minutes = (totalDurationSeconds % 3600) / 60;
        int seconds = totalDurationSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
