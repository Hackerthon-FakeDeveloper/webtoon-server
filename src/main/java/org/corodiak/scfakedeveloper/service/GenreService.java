package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.GenreDto;
import org.corodiak.scfakedeveloper.type.vo.GenreVo;

public interface GenreService {
	@Transactional
	boolean addGenre(GenreDto genreDto);

	@Transactional
	GenreVo findGenre(Long seq) throws SearchResultNotExistException;

	@Transactional
	List<GenreVo> findAll();

	@Transactional
	void removeGenre(Long seq);
}
