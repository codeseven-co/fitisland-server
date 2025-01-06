package happyperson.fitisland.domain.user.repository;

import happyperson.fitisland.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByNickname(String nickname);
}
