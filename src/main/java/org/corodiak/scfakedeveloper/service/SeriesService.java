package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.vo.SeriesVo;

import javax.transaction.Transactional;
import java.util.List;

public interface SeriesService {
    @Transactional
    boolean addSeries(String title, String description);

    @Transactional
    SeriesVo findSeries(Long seq);

    @Transactional
    List<SeriesVo> findAll();

    @Transactional
    List<SeriesVo> search(String keyword);

    @Transactional
    boolean updateSeries(Long seq, String title, String description);

    @Transactional
    void removeSeries(Long seq);
}
