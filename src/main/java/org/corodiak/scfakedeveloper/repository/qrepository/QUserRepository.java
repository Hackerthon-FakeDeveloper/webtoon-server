package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.User;

public interface QUserRepository {
	List<User> findAll(Long start, Long display);
}
