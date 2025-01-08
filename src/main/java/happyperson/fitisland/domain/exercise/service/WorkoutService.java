package happyperson.fitisland.domain.exercise.service;

import happyperson.fitisland.domain.exercise.dto.response.workout.WorkoutResponse;
import happyperson.fitisland.domain.exercise.entity.workout.WorkoutRecord;
import happyperson.fitisland.domain.exercise.repository.WorkoutRecordQuery;
import happyperson.fitisland.domain.exercise.repository.WorkoutRecordRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.exception.UserNotFoundException;
import happyperson.fitisland.domain.user.repository.UserRepository;
import happyperson.fitisland.global.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final UserRepository userRepository;
    private final WorkoutRecordRepository workoutRecordRepository;
    private final WorkoutRecordQuery workoutRecordQuery;


    public List<WorkoutRecord> getWorkoutRecordsByDate(Long userId, LocalDate date) {
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN); // 해당 날짜의 00:00:00
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX);   // 해당 날짜의 23:59:59
        return workoutRecordRepository.findByCreatedByAndCreatedDateTimeBetween(userId, startOfDay, endOfDay);
    }

    public List<WorkoutResponse.CountDto> getWorkoutRecordCountForAWeek(UserDetails userDetails, LocalDate startAt, LocalDate endAt) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(UserNotFoundException::new);
        List<WorkoutRecord> workoutRecords = workoutRecordQuery.getWorkoutRecordCountForAWeek(user.getId(), startAt, endAt);
        // 날짜 별 운동 기록을 map 형태로 변환한다.
        Map<String, Long> countMap = workoutRecords.stream()
                .collect(Collectors.groupingBy(
                                wr -> DateUtil.toLocalDateStr(wr.getStartTime()),
                                Collectors.counting()
                        )
                );
        // map 형태로 기록된 기록을 list로 변환해준다.
        return countMap.entrySet().stream()
                .map(cm -> new WorkoutResponse.CountDto(cm.getKey(), cm.getValue()))
                .collect(Collectors.toList());
    }
}
