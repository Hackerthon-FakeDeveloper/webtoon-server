package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.ReviewDto;
import org.corodiak.scfakedeveloper.type.vo.ReviewVo;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewService {
    boolean addReview(ReviewDto reviewDto);

    ReviewVo findReview(Long seq) throws SearchResultNotExistException;

    List<ReviewVo> findByUserSeq(Long seq, Long start, Long display);

    List<ReviewVo> findByWebtoonSeq(Long seq, Long start, Long display);

    @Transactional
    boolean updateReview(ReviewDto reviewDto);

    void removeReview(Long seq);

    @Transactional
    void likeReview(Long userSeq, Long reviewSeq);

    @Transactional
    void dislikeReview(Long userSeq, Long reviewSeq);
}
