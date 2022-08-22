package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.NotAllowValueException;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.UserDto;
import org.corodiak.scfakedeveloper.type.vo.UserVo;
import org.corodiak.scfakedeveloper.type.vo.ViewHistoryVo;

public interface UserService {
	@Transactional
	UserVo findUser(Long seq) throws SearchResultNotExistException;

	@Transactional
	List<UserVo> findAll(Long start, Long display);

	@Transactional
	boolean updateUser(UserDto userDto) throws SearchResultNotExistException, NotAllowValueException;

	@Transactional
	void removeUser(Long seq);

	@Transactional
	void addViewHistory(Long userSeq, Long webtoonSeq);

	@Transactional
	List<ViewHistoryVo> findViewHistory(Long seq);

	@Transactional
	void removeViewHistory(Long userSeq, Long webtoonSeq);

	@Transactional
	boolean userInfoIsSet(Long userSeq);
}
