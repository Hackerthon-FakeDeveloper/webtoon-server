package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.User;

import java.util.List;

public interface QUserRepository {
    List<User> findAll(Long start, Long display);
}
