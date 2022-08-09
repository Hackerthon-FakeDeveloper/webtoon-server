package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.type.vo.ReviewVo;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewService {
    boolean addReview(int scoreFirst, int scoreSecond, int scoreThird,
                      String content, Long userSeq, Long webtoonSeq);

    ReviewVo findReview(Long seq);

    List<ReviewVo> findByUserSeq(Long seq, Long start, Long display);

    List<ReviewVo> findByWebtoonSeq(Long seq, Long start, Long display);

    @Transactional
    boolean updateReview(Long seq, int scoreFirst, int scoreSecond,
                         int scoreThird, String content);

    void removeReview(Long seq);
}
