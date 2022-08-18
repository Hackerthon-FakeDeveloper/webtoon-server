package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QWebtoonLikeRepository;
import org.corodiak.scfakedeveloper.type.entity.WebtoonLike;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonLikeRepository extends JpaRepository<WebtoonLike, WebtoonLikeId>, QWebtoonLikeRepository {
}
