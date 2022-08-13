package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface WebtoonService {
    @Transactional
    boolean addWebtoon(String title, String platform, boolean isAdult, String thumbnail,
                       String url, String description, LocalDate startDate, Long authorSeq);

    @Transactional
    WebtoonVo findWebtoon(Long seq);

    @Transactional
    List<WebtoonVo> findByAuthorSeq(Long seq);

    @Transactional
    List<WebtoonVo> findByPlatform(String platform, Long start, Long display);

    @Transactional
    List<WebtoonVo> findAll(Long start, Long display);

    @Transactional
    List<WebtoonVo> search(String keyword, Long start, Long display);

    @Transactional
    boolean updateWebtoon(Long seq, String title, String platform, boolean isAdult, String thumbnail,
                          String url, String description, LocalDate startDate, Long authorSeq);

    @Transactional
    void removeWebtoon(Long seq);

    @Transactional
    void likeWebtoon(Long userSeq, Long webtoonSeq);

    @Transactional
    void dislikeWebtoon(Long userSeq, Long webtoonSeq);
}
