package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.WebtoonTag;

public interface QWebtoonTagRepository {
	List<WebtoonTag> findByTagSeq(Long seq, Long start, Long display);

    List<WebtoonTag> findByTagString(String tag, Long start, Long display);
}
