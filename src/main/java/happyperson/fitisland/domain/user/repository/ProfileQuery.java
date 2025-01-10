package happyperson.fitisland.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import happyperson.fitisland.domain.user.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static happyperson.fitisland.domain.user.entity.QProfile.*;
import static happyperson.fitisland.domain.user.entity.QUser.*;

@Repository
@RequiredArgsConstructor
public class ProfileQuery {

    private final JPAQueryFactory queryFactory;

    public Profile findRecentProfile(Long userId) {
        return queryFactory
                .select(profile)
                .from(profile)
                .join(user).on(profile.user.id.eq(userId))
                .orderBy(
                        profile.date.desc(),
                        profile.id.desc()
                )
                .limit(1)
                .fetchOne();
    }
}
