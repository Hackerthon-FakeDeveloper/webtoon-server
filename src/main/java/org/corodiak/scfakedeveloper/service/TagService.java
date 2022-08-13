package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.TagDto;
import org.corodiak.scfakedeveloper.type.vo.TagVo;

public interface TagService {
	@Transactional
	boolean addTag(TagDto tagDto);

	@Transactional
	TagVo findTag(Long seq) throws SearchResultNotExistException;

	@Transactional
	List<TagVo> findAll();

	@Transactional
	void removeTag(Long seq);
}
