package happyperson.fitisland.domain.exercise.dto.response.workout;

import lombok.Data;

public class WorkoutResponse {

    @Data
    public static class CountDto {
        private String date;
        private Long count;

        public CountDto(String date, Long count) {
            this.date = date;
            this.count = count;
        }
    }
}
