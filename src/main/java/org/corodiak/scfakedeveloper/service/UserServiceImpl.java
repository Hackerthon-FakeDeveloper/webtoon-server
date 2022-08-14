package org.corodiak.scfakedeveloper.service;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.UserRepository;
import org.corodiak.scfakedeveloper.repository.ViewHistoryRepository;
import org.corodiak.scfakedeveloper.type.dto.UserDto;
import org.corodiak.scfakedeveloper.type.entity.User;
import org.corodiak.scfakedeveloper.type.entity.ViewHistory;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.entity.id.ViewHistoryId;
import org.corodiak.scfakedeveloper.type.vo.UserVo;
import org.corodiak.scfakedeveloper.type.vo.ViewHistoryVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ViewHistoryRepository viewHistoryRepository;

    @Override
    @Transactional
    public UserVo findUser(Long seq) throws SearchResultNotExistException {
        Optional<User> user = userRepository.findById(seq);
        if (user.isPresent()) {
            return new UserVo(user.get());
        }
        throw new SearchResultNotExistException();
    }

    @Override
    @Transactional
    public List<UserVo> findAll(Long start, Long display) {
        List<User> userList = userRepository.findAll(start, display);
        List<UserVo> results = userList.stream()
                .map(e -> new UserVo(e))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public boolean updateUser(UserDto userDto) throws SearchResultNotExistException {
        Optional<User> user = userRepository.findById(userDto.getSeq());
        if (user.isEmpty()) {
            throw new SearchResultNotExistException();
        }

        User result = user.get();
        result.setNickname(userDto.getNickname());

        return true;
    }

    @Override
    @Transactional
    public void removeUser(Long seq) {
        userRepository.deleteById(seq);
    }

    @Override
    @Transactional
    public void addViewHistory(Long userSeq, Long webtoonSeq) {
        ViewHistory viewHistory = ViewHistory.builder()
                .user(User.builder().seq(userSeq).build())
                .webtoon(Webtoon.builder().seq(webtoonSeq).build())
                .build();
        viewHistoryRepository.save(viewHistory);
    }

    @Override
    @Transactional
    public List<ViewHistoryVo> findViewHistroy(Long seq) {
        List<ViewHistory> viewHistoryList = viewHistoryRepository.findByUserSeq(seq);
        List<ViewHistoryVo> results = viewHistoryList.stream()
                .map(e -> new ViewHistoryVo(e))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public void removeViewHistory(Long userSeq, Long webtoonSeq) {
        ViewHistoryId viewHistoryId = ViewHistoryId.builder()
                .user(userSeq)
                .webtoon(webtoonSeq)
                .build();
        viewHistoryRepository.deleteById(viewHistoryId);
    }
}
