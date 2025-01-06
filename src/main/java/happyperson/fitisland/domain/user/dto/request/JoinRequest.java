package happyperson.fitisland.domain.user.dto.request;

import happyperson.fitisland.domain.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinRequest {
    private String email;
    private String password;
    private String nickname;

    public User toEntity(String encode) {
        return User.builder()
                .username(email)
                .password(encode)
                .email(email)
                .name(null)
                .nickname(nickname)
                .role("USER")
                .createdAt(LocalDateTime.now())
                .lastLoginAt(LocalDateTime.now())
                .build();
    }
}
