package happyperson.fitisland.domain.user.repository;

import happyperson.fitisland.domain.user.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Long> {
    Optional<Social> findByProviderAndProviderId(String provider, String providerId);
}
