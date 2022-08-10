package org.corodiak.scfakedeveloper.service;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.WebtoonRepository;
import org.corodiak.scfakedeveloper.type.entity.Author;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebtoonServiceImpl implements WebtoonService {

    private final WebtoonRepository webtoonRepository;

    @Override
    @Transactional
    public boolean addWebtoon(String title, String platform, boolean isAdult, String thumbnail,
                              String url, String description, LocalDate startDate, Long authorSeq) {
        Webtoon webtoon = Webtoon.builder()
                .title(title)
                .platform(platform)
                .isAdult(isAdult)
                .thumbnail(thumbnail)
                .url(url)
                .description(description)
                .startDate(startDate)
                .author(Author.builder().seq(authorSeq).build())
                .build();
        webtoonRepository.save(webtoon);
        return true;
    }

    @Override
    @Transactional
    public WebtoonVo findWebtoon(Long seq) {
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
    public List<WebtoonVo> findAll(Long start, Long display) {
        List<Webtoon> webtoonList = webtoonRepository.findAll(start, display);
        List<WebtoonVo> results = webtoonList.stream()
                .map(e -> new WebtoonVo.WebtoonVoWithAuthor(e))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public boolean updateWebtoon(Long seq, String title, String platform, boolean isAdult, String thumbnail,
                                 String url, String description, LocalDate startDate, Long authorSeq) {
        Optional<Webtoon> webtoon = webtoonRepository.findBySeq(seq);
        if (!webtoon.isPresent()) {
            throw new SearchResultNotExistException();
        }

        Webtoon result = webtoon.get();
        result.setTitle(title);
        result.setPlatform(platform);
        result.setAdult(isAdult);
        result.setThumbnail(thumbnail);
        result.setUrl(url);
        result.setDescription(description);
        result.setStartDate(startDate);
        result.setAuthor(Author.builder().seq(authorSeq).build());

        return true;
    }

    @Override
    @Transactional
    public void removeWebtoon(Long seq) {
        webtoonRepository.deleteById(seq);
    }
}
