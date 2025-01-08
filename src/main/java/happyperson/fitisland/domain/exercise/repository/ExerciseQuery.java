package happyperson.fitisland.domain.exercise.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import happyperson.fitisland.domain.exercise.dto.response.exerciseguide.ExerciseSearch;
import happyperson.fitisland.domain.exercise.entity.exerciseguide.ExerciseGuide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static happyperson.fitisland.domain.exercise.entity.exerciseguide.QExerciseGuide.*;
import static happyperson.fitisland.domain.exercise.entity.workout.QExerciseGuidePart.*;
import static happyperson.fitisland.domain.exercise.entity.workout.QExercisePart.*;
import static happyperson.fitisland.domain.exercise.entity.workout.QExercisePartType.*;
import static happyperson.fitisland.domain.exercise.entity.workout.QExerciseType.*;

@Repository
@RequiredArgsConstructor
public class ExerciseQuery {
    private final JPAQueryFactory queryFactory;

    public List<ExerciseGuide> findExercisesBySearch(ExerciseSearch search) {
        return queryFactory
                .select(exerciseGuide)
                .from(exerciseGuide)
                .join(exerciseGuidePart).on(exerciseGuidePart.exercise.id.eq(exerciseGuide.id))
                .join(exercisePart).on(exerciseGuidePart.exercisePart.id.eq(exercisePart.id))
                .join(exercisePartType).on(exercisePartType.exercisePart.id.eq(exercisePart.id))
                .join(exerciseType).on(exercisePartType.exerciseType.id.eq(exerciseType.id))
                .where(
                        partEq(search.getPartIds()),
                        typeEq(search.getTypeIds())
                )
                .fetch();
    }

    private BooleanExpression partEq(List<Long> partIds) {
        return partIds.isEmpty() ? null : exercisePart.id.in(partIds);
    }

    private BooleanExpression typeEq(List<Long> typeIds) {
        return typeIds.isEmpty() ? null : exerciseType.id.in(typeIds);
    }
}
