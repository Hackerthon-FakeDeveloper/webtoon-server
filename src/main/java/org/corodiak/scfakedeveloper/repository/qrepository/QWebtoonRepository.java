package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.Webtoon;

import java.util.List;
import java.util.Optional;

public interface QWebtoonRepository {
    Optional<Webtoon> findBySeq(Long seq);

    List<Webtoon> findByAuthorSeq(Long seq);

    List<Webtoon> findAll(Long start, Long display);
}
