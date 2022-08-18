package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;

import java.util.List;

public interface QWebtoonSeriesRepository {
    List<WebtoonSeries> findBySeriesSeq(Long seq);
}
