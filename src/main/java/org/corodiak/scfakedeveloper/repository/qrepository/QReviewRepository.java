package org.corodiak.scfakedeveloper.repository.qrepository;

import org.corodiak.scfakedeveloper.type.entity.Review;

import java.util.List;
import java.util.Optional;

public interface QReviewRepository {
    Optional<Review> findBySeq(Long seq);

    List<Review> findByUserSeq(Long seq, Long start, Long display);

    List<Review> findByWebtoonSeq(Long seq, Long start, Long display);
}
