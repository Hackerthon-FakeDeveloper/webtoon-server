package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QGenreRepository;
import org.corodiak.scfakedeveloper.type.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, QGenreRepository {
}
