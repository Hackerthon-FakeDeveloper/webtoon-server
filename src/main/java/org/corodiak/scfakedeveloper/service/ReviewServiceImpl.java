package org.corodiak.scfakedeveloper.service;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.repository.ReviewRepository;
import org.corodiak.scfakedeveloper.type.entity.Review;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

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
}
