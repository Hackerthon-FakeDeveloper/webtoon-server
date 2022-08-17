package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.StillCutDto;
import org.corodiak.scfakedeveloper.type.vo.StillCutVo;

public interface StillCutService {
	@Transactional
	boolean addStillCut(StillCutDto stillCutDto);

	@Transactional
	StillCutVo findStillCut(Long seq) throws SearchResultNotExistException;

	@Transactional
	List<StillCutVo> findByWebtoonSeq(Long seq);

	@Transactional
	boolean updateStillCut(StillCutDto stillCutDto) throws SearchResultNotExistException;

	@Transactional
	void removeStillCut(Long seq);
}
