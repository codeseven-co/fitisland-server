package happyperson.fitisland.domain.oauthjwt.repository;

import happyperson.fitisland.domain.oauthjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
