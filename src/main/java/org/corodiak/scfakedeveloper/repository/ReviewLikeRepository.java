package org.corodiak.scfakedeveloper.repository;

import org.corodiak.scfakedeveloper.repository.qrepository.QReviewListRepository;
import org.corodiak.scfakedeveloper.type.entity.ReviewLike;
import org.corodiak.scfakedeveloper.type.entity.id.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId>, QReviewListRepository {
}
