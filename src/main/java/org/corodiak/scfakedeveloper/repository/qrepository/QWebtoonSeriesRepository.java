package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;
import java.util.Optional;

import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;

public interface QWebtoonSeriesRepository {
	List<WebtoonSeries> findBySeriesSeq(Long seq);

	Optional<WebtoonSeries> findByWebtoonSeq(Long seq);
}
