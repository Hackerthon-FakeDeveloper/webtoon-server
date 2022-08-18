package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.WebtoonTag;

import java.util.List;

public interface QWebtoonTagRepository {
    List<WebtoonTag> findByTagSeq(Long seq);
}
