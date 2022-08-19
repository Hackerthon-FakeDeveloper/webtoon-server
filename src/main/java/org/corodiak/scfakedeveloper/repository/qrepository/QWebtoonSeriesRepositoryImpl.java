package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.QSeries;
import org.corodiak.scfakedeveloper.type.entity.QWebtoon;
import org.corodiak.scfakedeveloper.type.entity.QWebtoonSeries;
import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;

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
}
