package happyperson.fitisland.domain.exercise.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import happyperson.fitisland.domain.exercise.entity.workout.WorkoutRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static happyperson.fitisland.domain.exercise.entity.workout.QWorkoutRecord.*;
import static happyperson.fitisland.domain.user.entity.QUser.*;

@Repository
@RequiredArgsConstructor
public class WorkoutRecordQuery {
    private final JPAQueryFactory queryFactory;

    public List<WorkoutRecord> getWorkoutRecordCountForAWeek(Long userid, LocalDate startAt, LocalDate endAt) {
        return queryFactory
                .select(workoutRecord)
                .from(workoutRecord)
                .join(user).on(workoutRecord.user.id.eq(user.id))
                .where(
                        user.id.eq(userid),
                        // 운동 기록은 운동 시작 시간을 기준으로 남긴다.
                        workoutRecord.startTime.between(
                                startAt.atStartOfDay(),
                                LocalDateTime.of(endAt, LocalTime.MAX).withNano(0)
                        )
                )
                .fetch();
    }
}
