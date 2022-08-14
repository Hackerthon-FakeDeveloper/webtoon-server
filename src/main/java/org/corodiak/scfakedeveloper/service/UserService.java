package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.UserDto;
import org.corodiak.scfakedeveloper.type.vo.UserVo;
import org.corodiak.scfakedeveloper.type.vo.ViewHistoryVo;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    @Transactional
    UserVo findUser(Long seq) throws SearchResultNotExistException;

    @Transactional
    List<UserVo> findAll(Long start, Long display);

    @Transactional
    boolean updateUser(UserDto userDto) throws SearchResultNotExistException;

    @Transactional
    void removeUser(Long seq);

    @Transactional
    void addViewHistory(Long userSeq, Long webtoonSeq);

    @Transactional
    List<ViewHistoryVo> findViewHistroy(Long seq);

    @Transactional
    void removeViewHistory(Long userSeq, Long webtoonSeq);
}
