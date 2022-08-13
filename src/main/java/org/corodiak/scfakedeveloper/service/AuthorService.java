package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.dto.AuthorDto;
import org.corodiak.scfakedeveloper.type.vo.AuthorVo;

import javax.transaction.Transactional;
import java.util.List;

public interface AuthorService {
    boolean addAuthor(AuthorDto authorDto);

    List<AuthorVo> findAll();

    AuthorVo findAuthor(Long seq);

    void removeAuthor(Long seq);

    @Transactional
    boolean updateAuthor(AuthorDto authorDto);

    List<AuthorVo> searchAuthor(String keyword);
}
