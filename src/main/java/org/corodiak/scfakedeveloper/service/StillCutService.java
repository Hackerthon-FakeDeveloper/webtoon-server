package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.dto.StillCutDto;
import org.corodiak.scfakedeveloper.type.vo.StillCutVo;

import javax.transaction.Transactional;
import java.util.List;

public interface StillCutService {
    @Transactional
    boolean addStillCut(StillCutDto stillCutDto);

    @Transactional
    StillCutVo findStillCut(Long seq);

    @Transactional
    List<StillCutVo> findByWebtoonSeq(Long seq);

    @Transactional
    boolean updateStillCut(StillCutDto stillCutDto);

    @Transactional
    void removeStillCut(Long seq);
}
