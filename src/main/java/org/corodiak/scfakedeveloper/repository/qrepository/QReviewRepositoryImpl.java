package org.corodiak.scfakedeveloper.repository.qrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.type.entity.QReview;
import org.corodiak.scfakedeveloper.type.entity.Review;

import java.util.List;

@RequiredArgsConstructor
public class QReviewRepositoryImpl implements QReviewRepository {

    private final JPAQueryFactory queryFactory;
    private QReview qReview = QReview.review;

    @Override
    public List<Review> findByUserSeq(Long seq, Long start, Long display) {
        List<Review> results = queryFactory.selectFrom(qReview)
                .where(qReview.user.seq.eq(seq))
                .offset(start)
                .limit(display)
                .fetch();
        return results;
    }

    @Override
    public List<Review> findByWebtoonSeq(Long seq, Long start, Long display) {
        List<Review> results = queryFactory.selectFrom(qReview)
                .where(qReview.webtoon.seq.eq(seq))
                .offset(start)
                .limit(display)
                .fetch();
        return results;
    }
}
