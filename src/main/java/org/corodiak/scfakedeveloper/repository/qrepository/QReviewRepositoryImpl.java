package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;
import java.util.Optional;

import org.corodiak.scfakedeveloper.type.entity.QReview;
import org.corodiak.scfakedeveloper.type.entity.QUser;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.Review;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QReviewRepositoryImpl implements QReviewRepository {

	private final JPAQueryFactory queryFactory;
	private QReview qReview = QReview.review;
	private QUser qUser = QUser.user;
	private QWebtoon qWebtoon = QWebtoon.webtoon;

	@Override
	public Optional<Review> findBySeq(Long seq) {
		Review result = queryFactory.selectFrom(qReview)
			.where(qReview.seq.eq(seq))
			.innerJoin(qReview.user, qUser)
			.innerJoin(qReview.webtoon, qWebtoon)
			.fetchJoin()
			.fetchOne();
		return Optional.ofNullable(result);
	}

	@Override
	public List<Review> findByUserSeq(Long seq, Long start, Long display) {
		List<Review> results = queryFactory.selectFrom(qReview)
			.where(qReview.user.seq.eq(seq))
			.innerJoin(qReview.user, qUser)
			.innerJoin(qReview.webtoon, qWebtoon)
			.fetchJoin()
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Review> findByWebtoonSeq(Long seq, Long start, Long display) {
		List<Review> results = queryFactory.selectFrom(qReview)
			.where(qReview.webtoon.seq.eq(seq))
			.innerJoin(qReview.user, qUser)
			.innerJoin(qReview.webtoon, qWebtoon)
			.fetchJoin()
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}
}
