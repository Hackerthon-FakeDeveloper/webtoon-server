package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.vo.AuthorVo;

import javax.transaction.Transactional;
import java.util.List;

public interface AuthorService {
    boolean addAuthor(String name, String description);

    List<AuthorVo> findAll();

    AuthorVo findAuthor(Long seq);

    void removeAuthor(Long seq);

    @Transactional
    boolean updateAuthor(Long seq, String name, String description);

    List<AuthorVo> searchAuthor(String keyword);
}
