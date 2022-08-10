package org.corodiak.scfakedeveloper.repository.qrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.type.entity.QSeries;
import org.corodiak.scfakedeveloper.type.entity.Series;

import java.util.List;

@RequiredArgsConstructor
public class QSeriesRepositoryImpl implements QSeriesRepository {

    private final JPAQueryFactory queryFactory;
    private QSeries qSeries = QSeries.series;

    @Override
    public List<Series> search(String keyword) {
        List<Series> results = queryFactory.selectFrom(qSeries)
                .where(qSeries.title.contains(keyword).or(qSeries.description.contains(keyword)))
                .fetch();
        return results;
    }
}
