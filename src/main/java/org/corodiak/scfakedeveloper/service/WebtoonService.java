package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.WebtoonDto;
import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;

public interface WebtoonService {
	@Transactional
	boolean addWebtoon(WebtoonDto webtoonDto);

	@Transactional
	WebtoonVo findWebtoon(Long seq) throws SearchResultNotExistException;

	@Transactional
	List<WebtoonVo> findByAuthorSeq(Long seq);

	@Transactional
	List<WebtoonVo> findByPlatform(String platform, Long start, Long display);

	@Transactional
	List<WebtoonVo> findAll(Long start, Long display);

	@Transactional
	List<WebtoonVo> search(String keyword, Long start, Long display);

	@Transactional
	boolean updateWebtoon(WebtoonDto webtoonDto) throws SearchResultNotExistException;

	@Transactional
	void removeWebtoon(Long seq);

	@Transactional
	void likeWebtoon(Long userSeq, Long webtoonSeq);

	@Transactional
	void dislikeWebtoon(Long userSeq, Long webtoonSeq);

	@Transactional
	List<WebtoonVo> findByLike(Long userSeq);

	@Transactional
	void addWebtoonSeries(Long webtoonSeq, Long seriesSeq);

	@Transactional
	List<WebtoonVo> findBySeries(Long seriesSeq);

	@Transactional
	void addWebtoonTag(Long webtoonSeq, Long tagSeq);

	@Transactional
	void removeWebtoonSeries(Long webtoonSeq, Long seriesSeq);

	@Transactional
	void removeWebtoonTag(Long webtoonSeq, Long tagSeq);

	@Transactional
	List<WebtoonVo> findByTagSeq(Long tagSeq);

	@Transactional
	List<WebtoonVo> findByTagString(String tag);

	@Transactional
	List<WebtoonVo> findNewWebtoon(Long start, Long display);
}
