package org.corodiak.scfakedeveloper.repository.qrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.type.entity.QUser;
import org.corodiak.scfakedeveloper.type.entity.User;

import java.util.List;

@RequiredArgsConstructor
public class QUserRepositoryImpl implements QUserRepository {

    private final JPAQueryFactory queryFactory;
    private QUser qUser = QUser.user;

    @Override
    public List<User> findAll(Long start, Long display) {
        List<User> results = queryFactory.selectFrom(qUser)
                .offset(start)
                .limit(display)
                .fetch();
        return results;
    }
}
