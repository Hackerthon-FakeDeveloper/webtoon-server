package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.WebtoonLike;

import java.util.List;

public interface QWebtoonLikeRepository {
    List<WebtoonLike> findByUserSeq(Long seq);
}
