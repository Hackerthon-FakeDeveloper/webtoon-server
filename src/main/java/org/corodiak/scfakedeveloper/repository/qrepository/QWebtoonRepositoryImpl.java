package org.corodiak.scfakedeveloper.repository.qrepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.corodiak.scfakedeveloper.type.entity.QAuthor;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.QWebtoonLike;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.entity.WebtoonLike;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QWebtoonRepositoryImpl implements QWebtoonRepository {

	private final JPAQueryFactory queryFactory;
	private QWebtoon qWebtoon = QWebtoon.webtoon;
	private QAuthor qAuthor = QAuthor.author;
	private QWebtoonLike qWebtoonLike = QWebtoonLike.webtoonLike;

	@Override
	public Optional<Webtoon> findBySeq(Long seq) {
		Webtoon result = queryFactory.selectFrom(qWebtoon)
			.where(qWebtoon.seq.eq(seq))
			.innerJoin(qWebtoon.author, qAuthor)
			.fetchJoin()
			.fetchOne();
		return Optional.ofNullable(result);
	}

	@Override
	public List<Webtoon> findByAuthorSeq(Long seq) {
		List<Webtoon> results = queryFactory.selectFrom(qWebtoon)
			.where(qWebtoon.author.seq.eq(seq))
			.innerJoin(qWebtoon.author, qAuthor)
			.fetchJoin()
			.fetch();
		return results;
	}

	@Override
	public List<Webtoon> findByPlatform(String platform, Long start, Long display) {
		List<Webtoon> results = queryFactory.selectFrom(qWebtoon)
			.where(qWebtoon.platform.eq(platform))
			.innerJoin(qWebtoon.author, qAuthor)
			.fetchJoin()
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Webtoon> findAll(Long start, Long display) {
		List<Webtoon> results = queryFactory.selectFrom(qWebtoon)
			.innerJoin(qWebtoon.author, qAuthor)
			.fetchJoin()
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Webtoon> search(String keyword, Long start, Long display) {
		List<Webtoon> results = queryFactory.selectFrom(qWebtoon)
			.where(qWebtoon.title.contains(keyword).or(qWebtoon.description.contains(keyword)))
			.innerJoin(qWebtoon.author, qAuthor)
			.fetchJoin()
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Webtoon> findNewWebtoon(Long start, Long display) {
		List<Webtoon> results = queryFactory.selectFrom(qWebtoon)
			.orderBy(qWebtoon.startDate.desc())
			.offset(start)
			.limit(display)
			.fetch();
		return results;
	}

	@Override
	public List<Webtoon> findPopularWebtoon(Long start, Long display) {
		List<WebtoonLike> webtoonLikeList = queryFactory.selectFrom(qWebtoonLike)
			.innerJoin(qWebtoonLike.webtoon, qWebtoon)
			.fetchJoin()
			.groupBy(qWebtoonLike.webtoon).orderBy(qWebtoonLike.count().desc())
			.offset(start).limit(display)
			.fetch();
		List<Webtoon> result = webtoonLikeList.stream().map(e -> e.getWebtoon()).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<Webtoon> findRecentPopularWebtoon(Long start, Long display) {
		List<WebtoonLike> webtoonLikeList = queryFactory.selectFrom(qWebtoonLike)
			.innerJoin(qWebtoonLike.webtoon, qWebtoon)
			.fetchJoin()
			.where(qWebtoonLike.createdDate.after(LocalDateTime.now().minusMonths(1)))
			.groupBy(qWebtoonLike.webtoon).orderBy(qWebtoonLike.count().desc())
			.offset(start).limit(display)
			.fetch();
		List<Webtoon> result = webtoonLikeList.stream().map(e -> e.getWebtoon()).collect(Collectors.toList());
		return result;
	}

}
