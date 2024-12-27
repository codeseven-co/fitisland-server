package happyperson.fitisland.domain.user.dto.response;

import happyperson.fitisland.common.util.DateUtil;
import happyperson.fitisland.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

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
}
