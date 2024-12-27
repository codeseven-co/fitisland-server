package happyperson.fitisland.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원 아이디
    private String username;

    // 비밀번호
    private String password;

    // 실명
    private String name;

    // 닉네임
    private String nickname;

    // 이메일
    private String email;

    // 스프링 시큐리티 내 권한
    private String role;

    // 계정 생성 일자
    @CreatedDate
    private LocalDateTime createdAt;

    // 마지막 로그인 시간
    @CreatedDate
    private LocalDateTime lastLoginAt;

    @Builder
    public User(
            Long id, String username, String password,
            String name, String nickname, String email, String role,
            LocalDateTime createdAt, LocalDateTime lastLoginAt
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
    }
}
