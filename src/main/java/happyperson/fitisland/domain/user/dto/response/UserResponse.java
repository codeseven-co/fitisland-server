package happyperson.fitisland.domain.user.dto.response;

import happyperson.fitisland.global.util.DateUtil;
import happyperson.fitisland.domain.user.entity.Profile;
import happyperson.fitisland.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

public class UserResponse {

    @Data
    public static class AccountDto {
        private Long id;
        private String email;
        private String name;
        private String nickname;
        private String sign_up;
        private String last_login;

        @Builder
        public AccountDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.nickname = user.getNickname();
            this.sign_up = DateUtil.toLocalDateStr(user.getCreatedAt());
            this.last_login = DateUtil.toStr(user.getLastLoginAt());
        }
    }

    @Data
    public static class ProfileDto {
        private Long id;
        private String gender;
        private LocalDate date;
        private Double height;
        private Double weight;
        private Double bodyFatPercentage;
        private String target;

        @Builder
        public ProfileDto(Profile profile) {
            this.id = profile.getId();
            this.gender = profile.getGender().toString();
            this.date = profile.getDate();
            this.height = profile.getHeight();
            this.weight = profile.getWeight();
            this.bodyFatPercentage = profile.getBodyFatPercentage();
            this.target = profile.getTarget().toString();
        }
    }
}
