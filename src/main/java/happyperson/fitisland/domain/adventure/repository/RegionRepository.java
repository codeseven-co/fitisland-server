package happyperson.fitisland.domain.adventure.repository;

import happyperson.fitisland.domain.adventure.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
