package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.ViewHistory;

public interface QViewHistoryRepository {
	List<ViewHistory> findByUserSeq(Long seq);
}
