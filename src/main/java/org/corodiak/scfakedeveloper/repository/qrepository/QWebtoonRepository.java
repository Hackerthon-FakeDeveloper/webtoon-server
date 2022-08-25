package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;
import java.util.Optional;

import org.corodiak.scfakedeveloper.type.entity.Webtoon;

public interface QWebtoonRepository {
	Optional<Webtoon> findBySeq(Long seq);

	List<Webtoon> findByAuthorSeq(Long seq);

	List<Webtoon> findByPlatform(String platform, Long start, Long display);

	List<Webtoon> findAll(Long start, Long display);

	List<Webtoon> search(String keyword, Long start, Long display);

	List<Webtoon> findNewWebtoon(Long start, Long display);
}
