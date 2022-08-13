package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.TagDto;
import org.corodiak.scfakedeveloper.type.vo.TagVo;

import javax.transaction.Transactional;
import java.util.List;

public interface TagService {
    @Transactional
    boolean addTag(TagDto tagDto);

    @Transactional
    TagVo findTag(Long seq) throws SearchResultNotExistException;

    @Transactional
    List<TagVo> findAll();

    @Transactional
    void removeTag(Long seq);
}
