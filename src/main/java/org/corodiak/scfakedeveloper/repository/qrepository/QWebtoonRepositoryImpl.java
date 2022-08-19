package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;
import java.util.Optional;

import org.corodiak.scfakedeveloper.type.entity.QAuthor;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QWebtoonRepositoryImpl implements QWebtoonRepository {

	private final JPAQueryFactory queryFactory;
	private QWebtoon qWebtoon = QWebtoon.webtoon;
	private QAuthor qAuthor = QAuthor.author;

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
}
