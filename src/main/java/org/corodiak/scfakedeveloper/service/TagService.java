package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.dto.TagDto;
import org.corodiak.scfakedeveloper.type.vo.TagVo;

import javax.transaction.Transactional;
import java.util.List;

public interface TagService {
    @Transactional
    boolean addTag(TagDto tagDto);

    @Transactional
    TagVo findTag(Long seq);

    @Transactional
    List<TagVo> findAll();

    @Transactional
    void removeTag(Long seq);
}
