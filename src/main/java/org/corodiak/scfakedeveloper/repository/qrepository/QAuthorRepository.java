package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.Author;

import java.util.List;

public interface QAuthorRepository {
    List<Author> search(String keyword);
}
