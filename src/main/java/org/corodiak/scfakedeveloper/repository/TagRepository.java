package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QTagRepository;
import org.corodiak.scfakedeveloper.type.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, QTagRepository {
}
