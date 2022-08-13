package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.Author;

public interface QAuthorRepository {
	List<Author> search(String keyword);
}
