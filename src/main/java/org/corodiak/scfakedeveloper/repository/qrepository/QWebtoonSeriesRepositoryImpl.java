package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;
import java.util.Optional;

import org.corodiak.scfakedeveloper.type.entity.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QWebtoonSeriesRepositoryImpl implements QWebtoonSeriesRepository {

	private final JPAQueryFactory queryFactory;
	private QWebtoonSeries qWebtoonSeries = QWebtoonSeries.webtoonSeries;
	private QWebtoon qWebtoon = QWebtoon.webtoon;
	private QSeries qSeries = QSeries.series;

	@Override
	public List<WebtoonSeries> findBySeriesSeq(Long seq) {
		List<WebtoonSeries> results = queryFactory.selectFrom(qWebtoonSeries)
			.where(qWebtoonSeries.series.seq.eq(seq))
			.innerJoin(qWebtoonSeries.series, qSeries)
			.innerJoin(qWebtoonSeries.webtoon, qWebtoon)
			.fetchJoin()
			.fetch();
		return results;
	}

	@Override
	public Optional<WebtoonSeries> findByWebtoonSeq(Long seq) {
		WebtoonSeries results = queryFactory.selectFrom(qWebtoonSeries)
				.where(qWebtoonSeries.webtoon.seq.eq(seq))
				.innerJoin(qWebtoonSeries.series, qSeries)
				.innerJoin(qWebtoonSeries.webtoon, qWebtoon)
				.fetchJoin()
				.fetchOne();
		return Optional.ofNullable(results);
	}
}
