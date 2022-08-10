package org.corodiak.scfakedeveloper.service;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.ReviewRepository;
import org.corodiak.scfakedeveloper.type.entity.Review;
import org.corodiak.scfakedeveloper.type.vo.ReviewVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public boolean addReview(int scoreFirst, int scoreSecond, int scoreThird,
                             String content, Long userSeq, Long webtoonSeq) {
        Review review = Review.builder()
                .scoreFirst(scoreFirst)
                .scoreSecond(scoreSecond)
                .scoreThird(scoreThird)
                .content(content)
                .userSeq(userSeq)
                .webtoonSeq(webtoonSeq)
                .build();
        reviewRepository.save(review);
        return true;
    }

    @Override
    @Transactional
    public ReviewVo findReview(Long seq) {
        Optional<Review> review = reviewRepository.findBySeq(seq);
        if (review.isPresent()) {
            return new ReviewVo.ReviewVoWithUserAndWebtoon(review.get());
        }
        throw new SearchResultNotExistException();
    }

    @Override
    @Transactional
    public List<ReviewVo> findByUserSeq(Long seq, Long start, Long display) {
        List<Review> reviewList = reviewRepository.findByUserSeq(seq, start, display);
        List<ReviewVo> results = reviewList.stream()
                .map(e -> new ReviewVo.ReviewVoWithUserAndWebtoon(e))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public List<ReviewVo> findByWebtoonSeq(Long seq, Long start, Long display) {
        List<Review> reviewList = reviewRepository.findByWebtoonSeq(seq, start, display);
        List<ReviewVo> results = reviewList.stream()
                .map(e -> new ReviewVo.ReviewVoWithUserAndWebtoon(e))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public boolean updateReview(Long seq, int scoreFirst, int scoreSecond,
                                int scoreThird, String content) {
        Optional<Review> review = reviewRepository.findById(seq);
        if (!review.isPresent()) {
            throw new SearchResultNotExistException();
        }

        Review result = review.get();
        result.setScoreFirst(scoreFirst);
        result.setScoreSecond(scoreSecond);
        result.setScoreThird(scoreThird);
        result.setContent(content);

        return true;
    }

    @Override
    @Transactional
    public void removeReview(Long seq) {
        reviewRepository.deleteById(seq);
    }
}
