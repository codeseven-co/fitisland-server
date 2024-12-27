package happyperson.fitisland.domain.adventure.repository;

import happyperson.fitisland.domain.adventure.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Long> {
}
