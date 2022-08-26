package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.type.entity.QAuthor;
import org.corodiak.scfakedeveloper.type.entity.QReview;
import org.corodiak.scfakedeveloper.type.entity.QUser;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.Review;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QReviewRepositoryImpl implements QReviewRepository {

	private final JPAQueryFactory queryFactory;
	private final EntityManager entityManager;
	private QReview qReview = QReview.review;
	private QUser qUser = QUser.user;
	private QWebtoon qWebtoon = QWebtoon.webtoon;
	private QAuthor qAuthor = QAuthor.author;

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

	@Override
	public Long countAll() {
		Long result = queryFactory
			.select(qReview.seq.count())
			.from(qReview)
			.fetchOne();
		return result;
	}

	@Override
	@Transactional
	public void batchUpdateReviewScore() {
		List<Tuple> tuples = queryFactory.select(qWebtoon, qReview.scoreFirst.avg(), qReview.scoreSecond.avg(),
				qReview.scoreThird.avg())
			.from(qReview)
			.innerJoin(qReview.webtoon, qWebtoon)
			.innerJoin(qWebtoon.author, qAuthor)
			.fetchJoin()
			.groupBy(qReview.webtoon)
			.fetch();
		for (Tuple tuple : tuples) {
			Webtoon webtoon = tuple.get(0, Webtoon.class);
			webtoon.setScoreFirstAverage(tuple.get(1, double.class));
			webtoon.setScoreSecondAverage(tuple.get(2, double.class));
			webtoon.setScoreThirdAverage(tuple.get(3, double.class));
			webtoon.setScoreTotalAverage(
				(webtoon.getScoreFirstAverage() + webtoon.getScoreSecondAverage() + webtoon.getScoreThirdAverage())
					/ 3);
			entityManager.persist(webtoon);
		}
		entityManager.flush();
	}
}
