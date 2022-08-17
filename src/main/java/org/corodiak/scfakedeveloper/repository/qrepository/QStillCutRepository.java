package org.corodiak.scfakedeveloper.repository.qrepository;

import java.util.List;

import org.corodiak.scfakedeveloper.type.entity.StillCut;

public interface QStillCutRepository {
	List<StillCut> findByWebtoonSeq(Long seq);
}
