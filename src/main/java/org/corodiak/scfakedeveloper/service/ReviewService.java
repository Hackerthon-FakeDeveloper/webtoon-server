package org.corodiak.scfakedeveloper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.PermissionDeniedException;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.ReviewDto;
import org.corodiak.scfakedeveloper.type.vo.ReviewVo;

public interface ReviewService {
	boolean addReview(ReviewDto reviewDto);

	ReviewVo findReview(Long seq) throws SearchResultNotExistException;

	List<ReviewVo> findByUserSeq(Long seq, Long start, Long display);

	List<ReviewVo> findByWebtoonSeq(Long seq, Long start, Long display);

	@Transactional
	boolean updateReview(ReviewDto reviewDto) throws SearchResultNotExistException;

	@Transactional
	void removeReviewAsUser(Long seq) throws PermissionDeniedException;

	@Transactional
	void removeReviewAsAdmin(Long seq);

	@Transactional
	void likeReview(Long userSeq, Long reviewSeq);

	@Transactional
	void dislikeReview(Long userSeq, Long reviewSeq);
}
