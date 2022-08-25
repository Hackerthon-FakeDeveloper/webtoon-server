package org.corodiak.scfakedeveloper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.SeriesRepository;
import org.corodiak.scfakedeveloper.repository.WebtoonSeriesRepository;
import org.corodiak.scfakedeveloper.type.dto.SeriesDto;
import org.corodiak.scfakedeveloper.type.entity.Series;
import org.corodiak.scfakedeveloper.type.entity.WebtoonSeries;
import org.corodiak.scfakedeveloper.type.vo.SeriesVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

	private final SeriesRepository seriesRepository;
	private final WebtoonSeriesRepository webtoonSeriesRepository;

	@Override
	@Transactional
	public boolean addSeries(SeriesDto seriesDto) {
		Series series = Series.builder()
			.title(seriesDto.getTitle())
			.description(seriesDto.getDescription())
			.build();
		seriesRepository.save(series);
		return true;
	}

	@Override
	@Transactional
	public SeriesVo findSeries(Long seq) throws SearchResultNotExistException {
		Optional<Series> series = seriesRepository.findById(seq);
		if (series.isPresent()) {
			return new SeriesVo(series.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public SeriesVo findByWebtoonSeq(Long seq) throws SearchResultNotExistException {
		Optional<WebtoonSeries> webtoonSeries = webtoonSeriesRepository.findByWebtoonSeq(seq);
		if (webtoonSeries.isPresent()) {
			return new SeriesVo(webtoonSeries.get().getSeries());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public List<SeriesVo> findAll() {
		List<Series> seriesList = seriesRepository.findAll();
		List<SeriesVo> results = seriesList.stream()
			.map(e -> new SeriesVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public List<SeriesVo> search(String keyword) {
		List<Series> seriesList = seriesRepository.search(keyword);
		List<SeriesVo> results = seriesList.stream()
			.map(e -> new SeriesVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public boolean updateSeries(SeriesDto seriesDto) {
		Optional<Series> series = seriesRepository.findById(seriesDto.getSeq());
		if (!series.isPresent()) {
			throw new SearchResultNotExistException();
		}

		Series result = series.get();
		result.setTitle(seriesDto.getTitle());
		result.setDescription(seriesDto.getDescription());

		return true;
	}

	@Override
	@Transactional
	public void removeSeries(Long seq) {
		seriesRepository.deleteById(seq);
	}
}
