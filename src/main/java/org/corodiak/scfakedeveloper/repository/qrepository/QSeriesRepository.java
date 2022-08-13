package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.Series;

public interface QSeriesRepository {
	List<Series> search(String keyword);
}
