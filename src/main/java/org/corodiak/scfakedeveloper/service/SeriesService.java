package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.SeriesDto;
import org.corodiak.scfakedeveloper.type.vo.SeriesVo;

public interface SeriesService {
	@Transactional
	boolean addSeries(SeriesDto seriesDto);

	@Transactional
	SeriesVo findSeries(Long seq) throws SearchResultNotExistException;

	@Transactional
	List<SeriesVo> findAll();

	@Transactional
	List<SeriesVo> search(String keyword);

	@Transactional
	boolean updateSeries(SeriesDto seriesDto);

	@Transactional
	void removeSeries(Long seq);
}
