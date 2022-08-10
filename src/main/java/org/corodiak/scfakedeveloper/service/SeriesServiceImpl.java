package org.corodiak.scfakedeveloper.service;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.SeriesRepository;
import org.corodiak.scfakedeveloper.type.entity.Series;
import org.corodiak.scfakedeveloper.type.vo.SeriesVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Override
    @Transactional
    public boolean addSeries(String title, String description) {
        Series series = Series.builder()
                .title(title)
                .description(description)
                .build();
        seriesRepository.save(series);
        return true;
    }

    @Override
    @Transactional
    public SeriesVo findSeries(Long seq) {
        Optional<Series> series = seriesRepository.findById(seq);
        if (series.isPresent()) {
            return new SeriesVo(series.get());
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
    public boolean updateSeries(Long seq, String title, String description) {
        Optional<Series> series = seriesRepository.findById(seq);
        if (!series.isPresent()) {
            throw new SearchResultNotExistException();
        }

        Series result = series.get();
        result.setTitle(title);
        result.setDescription(description);

        return true;
    }

    @Override
    @Transactional
    public void removeSeries(Long seq) {
        seriesRepository.deleteById(seq);
    }
}
