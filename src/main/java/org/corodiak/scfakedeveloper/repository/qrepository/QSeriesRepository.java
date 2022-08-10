package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.Series;

import java.util.List;

public interface QSeriesRepository {
    List<Series> search(String keyword);
}
