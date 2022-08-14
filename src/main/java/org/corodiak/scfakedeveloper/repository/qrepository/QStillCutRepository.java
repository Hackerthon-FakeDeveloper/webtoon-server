package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.StillCut;

import java.util.List;

public interface QStillCutRepository {
    List<StillCut> findByWebtoonSeq(Long seq);
}
