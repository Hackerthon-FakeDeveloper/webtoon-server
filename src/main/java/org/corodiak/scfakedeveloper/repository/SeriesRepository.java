package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QSeriesRepository;
import org.corodiak.scfakedeveloper.type.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>, QSeriesRepository {
}
