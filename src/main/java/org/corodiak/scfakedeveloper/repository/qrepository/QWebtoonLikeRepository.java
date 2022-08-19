package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.WebtoonLike;

public interface QWebtoonLikeRepository {
	List<WebtoonLike> findByUserSeq(Long seq);
}
