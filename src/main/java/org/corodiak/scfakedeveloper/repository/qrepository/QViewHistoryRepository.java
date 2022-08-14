package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.ViewHistory;

import java.util.List;

public interface QViewHistoryRepository {
    List<ViewHistory> findByUserSeq(Long seq);
}
