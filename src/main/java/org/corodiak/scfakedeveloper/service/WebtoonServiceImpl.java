package org.corodiak.scfakedeveloper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.WebtoonLikeRepository;
import org.corodiak.scfakedeveloper.repository.WebtoonRepository;
import org.corodiak.scfakedeveloper.repository.WebtoonSeriesRepository;
import org.corodiak.scfakedeveloper.repository.WebtoonTagRepository;
import org.corodiak.scfakedeveloper.type.dto.WebtoonDto;
import org.corodiak.scfakedeveloper.type.entity.Author;
import org.corodiak.scfakedeveloper.type.entity.Series;
import org.corodiak.scfakedeveloper.type.entity.Tag;
import org.corodiak.scfakedeveloper.type.entity.User;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.entity.WebtoonLike;
import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;
import org.corodiak.scfakedeveloper.type.entity.WebtoonTag;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonLikeId;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonSeriesId;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonTagId;
import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebtoonServiceImpl implements WebtoonService {

	private final WebtoonRepository webtoonRepository;
	private final WebtoonLikeRepository webtoonLikeRepository;
	private final WebtoonSeriesRepository webtoonSeriesRepository;
	private final WebtoonTagRepository webtoonTagRepository;

	@Override
	@Transactional
	public boolean addWebtoon(WebtoonDto webtoonDto) {
		Webtoon webtoon = Webtoon.builder()
			.title(webtoonDto.getTitle())
			.platform(webtoonDto.getPlatform())
			.isAdult(webtoonDto.isAdult())
			.thumbnail(webtoonDto.getThumbnail())
			.url(webtoonDto.getUrl())
			.description(webtoonDto.getDescription())
			.startDate(webtoonDto.getStartDate())
			.serialStatus(webtoonDto.getSerialStatus())
			.author(Author.builder().seq(webtoonDto.getAuthor()).build())
			.build();
		webtoonRepository.save(webtoon);
		return true;
	}

	@Override
	@Transactional
	public WebtoonVo findWebtoon(Long seq) throws SearchResultNotExistException {
		Optional<Webtoon> webtoon = webtoonRepository.findBySeq(seq);
		if (webtoon.isPresent()) {
			return new WebtoonVo.WebtoonVoWithAuthor(webtoon.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public List<WebtoonVo> findByAuthorSeq(Long seq) {
		List<Webtoon> webtoonList = webtoonRepository.findByAuthorSeq(seq);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findByPlatform(String platform, Long start, Long display) {
		List<Webtoon> webtoonList = webtoonRepository.findByPlatform(platform, start, display);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findAll(Long start, Long display) {
		List<Webtoon> webtoonList = webtoonRepository.findAll(start, display);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> search(String keyword, Long start, Long display) {
		List<Webtoon> webtoonList = webtoonRepository.search(keyword, start, display);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public boolean updateWebtoon(WebtoonDto webtoonDto) throws SearchResultNotExistException {
		Optional<Webtoon> webtoon = webtoonRepository.findBySeq(webtoonDto.getSeq());
		if (webtoon.isEmpty()) {
			throw new SearchResultNotExistException();
		}

		Webtoon result = webtoon.get();
		result.setTitle(webtoonDto.getTitle());
		result.setPlatform(webtoonDto.getPlatform());
		result.setAdult(webtoonDto.isAdult());
		result.setThumbnail(webtoonDto.getThumbnail());
		result.setUrl(webtoonDto.getUrl());
		result.setDescription(webtoonDto.getDescription());
		result.setStartDate(webtoonDto.getStartDate());
		result.setSerialStatus(webtoonDto.getSerialStatus());
		result.setAuthor(Author.builder().seq(webtoonDto.getAuthor()).build());

		return true;
	}

	@Override
	@Transactional
	public void removeWebtoon(Long seq) {
		webtoonRepository.deleteById(seq);
	}

	@Override
	@Transactional
	public void likeWebtoon(Long userSeq, Long webtoonSeq) {
		WebtoonLike webtoonLike = WebtoonLike.builder()
			.webtoon(Webtoon.builder().seq(webtoonSeq).build())
			.user(User.builder().seq(userSeq).build())
			.build();
		webtoonLikeRepository.save(webtoonLike);
	}

	@Override
	@Transactional
	public void dislikeWebtoon(Long userSeq, Long webtoonSeq) {
		WebtoonLikeId webtoonLikeId = WebtoonLikeId.builder()
			.webtoon(webtoonSeq)
			.user(userSeq)
			.build();
		webtoonLikeRepository.deleteById(webtoonLikeId);
	}

	@Override
	@Transactional
	public List<WebtoonVo> findByLike(Long userSeq, Long start, Long display) {
		List<WebtoonLike> webtoonLikeList = webtoonLikeRepository.findByUserSeq(userSeq, start, display);
		List<WebtoonVo> results = webtoonLikeList.stream()
			.map(e -> new WebtoonVo(e.getWebtoon()))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void addWebtoonSeries(Long webtoonSeq, Long seriesSeq) {
		WebtoonSeries webtoonSeries = WebtoonSeries.builder()
			.webtoon(Webtoon.builder().seq(webtoonSeq).build())
			.series(Series.builder().seq(seriesSeq).build())
			.build();
		webtoonSeriesRepository.save(webtoonSeries);
	}

	@Override
	@Transactional
	public void removeWebtoonSeries(Long webtoonSeq, Long seriesSeq) {
		WebtoonSeriesId webtoonSeriesId = WebtoonSeriesId.builder()
			.webtoon(webtoonSeq)
			.series(seriesSeq)
			.build();
		webtoonSeriesRepository.deleteById(webtoonSeriesId);
	}

	@Override
	@Transactional
	public List<WebtoonVo> findBySeries(Long seriesSeq) {
		List<WebtoonSeries> webtoonSeriesList = webtoonSeriesRepository.findBySeriesSeq(seriesSeq);
		List<WebtoonVo> results = webtoonSeriesList.stream()
			.map(e -> new WebtoonVo(e.getWebtoon()))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void addWebtoonTag(Long webtoonSeq, Long tagSeq) {
		WebtoonTag webtoonTag = WebtoonTag.builder()
			.webtoon(Webtoon.builder().seq(webtoonSeq).build())
			.tag(Tag.builder().seq(tagSeq).build())
			.build();
		webtoonTagRepository.save(webtoonTag);
	}

	@Override
	@Transactional
	public void removeWebtoonTag(Long webtoonSeq, Long tagSeq) {
		WebtoonTagId webtoonTagId = WebtoonTagId.builder()
			.webtoon(webtoonSeq)
			.tag(tagSeq)
			.build();
		webtoonTagRepository.deleteById(webtoonTagId);
	}

	@Override
	@Transactional
	public List<WebtoonVo> findByTagSeq(Long tagSeq, Long start, Long display) {
		List<WebtoonTag> webtoonTagList = webtoonTagRepository.findByTagSeq(tagSeq, start, display);
		List<WebtoonVo> results = webtoonTagList.stream()
			.map(e -> new WebtoonVo(e.getWebtoon()))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findByTagString(String tag, Long start, Long display) {
		List<WebtoonTag> webtoonTagList = webtoonTagRepository.findByTagString(tag, start, display);
		List<WebtoonVo> results = webtoonTagList.stream()
			.map(e -> new WebtoonVo(e.getWebtoon()))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findNewWebtoon(Long start, Long display) {
		List<Webtoon> webtoonList = webtoonRepository.findNewWebtoon(start, display);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findPopularWebtoon(Long start, Long display) {
		List<Webtoon> webtoonList = webtoonRepository.findPopularWebtoon(start, display);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findRecentPopularWebtoon(Long start, Long display) {
		List<Webtoon> webtoonList = webtoonRepository.findRecentPopularWebtoon(start, display);
		List<WebtoonVo> results = webtoonList.stream()
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<WebtoonVo> findRelatedWebtoon(Long webtoonSeq) {
		List<Webtoon> result;
		Optional<WebtoonSeries> seriesSeq = webtoonSeriesRepository.findByWebtoonSeq(webtoonSeq);
		if (seriesSeq.isPresent()) {
			List<WebtoonSeries> series = webtoonSeriesRepository.findBySeriesSeq(seriesSeq.get().getSeries().getSeq());
			result = series.stream().map(e -> e.getWebtoon()).filter(webtoon -> webtoon.getSeq() != webtoonSeq).collect(
				Collectors.toList());
		} else {
			List<WebtoonTag> tags = webtoonTagRepository.findByWebtoon(webtoonSeq);
			List<Long> tagSeqList = tags.stream().map(e -> e.getTag().getSeq()).collect(Collectors.toList());
			result = webtoonRepository.findRelatedTagWebtoon(tagSeqList);
		}
		return result.stream()
			.filter(webtoon -> webtoon.getSeq() != webtoonSeq)
			.map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
			.collect(
				Collectors.toList());
	}
}
