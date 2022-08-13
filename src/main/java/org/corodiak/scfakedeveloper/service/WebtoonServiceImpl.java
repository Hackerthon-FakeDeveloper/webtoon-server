package org.corodiak.scfakedeveloper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.WebtoonLikeRepository;
import org.corodiak.scfakedeveloper.repository.WebtoonRepository;
import org.corodiak.scfakedeveloper.type.dto.WebtoonDto;
import org.corodiak.scfakedeveloper.type.entity.Author;
import org.corodiak.scfakedeveloper.type.entity.User;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.entity.WebtoonLike;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonLikeId;
import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebtoonServiceImpl implements WebtoonService {

	private final WebtoonRepository webtoonRepository;
	private final WebtoonLikeRepository webtoonLikeRepository;

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
}
