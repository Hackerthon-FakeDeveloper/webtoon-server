package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QWebtoonTagRepository;
import org.corodiak.scfakedeveloper.type.entity.WebtoonTag;
import org.corodiak.scfakedeveloper.type.entity.id.WebtoonTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonTagRepository extends JpaRepository<WebtoonTag, WebtoonTagId>, QWebtoonTagRepository {
}
