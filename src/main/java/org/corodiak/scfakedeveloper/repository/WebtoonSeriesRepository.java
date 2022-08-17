package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QWebtoonSeriesRepository;
import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonSeriesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonSeriesRepository
	extends JpaRepository<WebtoonSeries, WebtoonSeriesId>, QWebtoonSeriesRepository {
}
