package happyperson.fitisland.domain.exercise.entity;

import happyperson.fitisland.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MysSQL -> AUTO_INCREMENT
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
