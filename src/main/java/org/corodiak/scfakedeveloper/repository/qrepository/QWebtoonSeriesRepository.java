package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;

public interface QWebtoonSeriesRepository {
	List<WebtoonSeries> findBySeriesSeq(Long seq);
}
