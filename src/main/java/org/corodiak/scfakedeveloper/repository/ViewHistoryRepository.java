package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QViewHistoryRepository;
import org.corodiak.scfakedeveloper.type.entity.ViewHistory;
import org.corodiak.scfakedeveloper.type.entity.id.ViewHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewHistoryRepository extends JpaRepository<ViewHistory, ViewHistoryId>, QViewHistoryRepository {
}
