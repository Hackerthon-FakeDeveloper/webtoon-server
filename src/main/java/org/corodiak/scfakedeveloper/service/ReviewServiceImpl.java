package org.corodiak.scfakedeveloper.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.auth.util.AuthUtil;
import org.corodiak.scfakedeveloper.exception.PermissionDeniedException;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.ReviewLikeRepository;
import org.corodiak.scfakedeveloper.repository.ReviewRepository;
import org.corodiak.scfakedeveloper.type.dto.ReviewDto;
import org.corodiak.scfakedeveloper.type.entity.Review;
import org.corodiak.scfakedeveloper.type.entity.ReviewLike;
import org.corodiak.scfakedeveloper.type.entity.User;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.entity.id.ReviewLikeId;
import org.corodiak.scfakedeveloper.type.vo.DetailReviewScoreVo;
import org.corodiak.scfakedeveloper.type.vo.ReviewVo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final ReviewLikeRepository reviewLikeRepository;
	private final static String[] paths = {"10_review.json", "20_review.json", "30_review.json", "m_review.json",
		"f_review.json"};

	@Override
	@Transactional
	public boolean addReview(ReviewDto reviewDto) {
		Review review = Review.builder()
			.scoreFirst(reviewDto.getScoreFirst())
			.scoreSecond(reviewDto.getScoreSecond())
			.scoreThird(reviewDto.getScoreThird())
			.content(reviewDto.getContent())
			.user(User.builder().seq(reviewDto.getUser()).build())
			.webtoon(Webtoon.builder().seq(reviewDto.getWebtoon()).build())
			.build();
		reviewRepository.save(review);
		return true;
	}

	@Override
	@Transactional
	public ReviewVo findReview(Long seq) throws SearchResultNotExistException {
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
	public Long getReviewCount() {
		Long count = reviewRepository.countAll();
		return count;
	}

	@Override
	@Transactional
	public boolean updateReview(ReviewDto reviewDto) throws SearchResultNotExistException {
		Optional<Review> review = reviewRepository.findById(reviewDto.getSeq());
		if (review.isEmpty()) {
			throw new SearchResultNotExistException();
		}

		Review result = review.get();
		result.setScoreFirst(reviewDto.getScoreFirst());
		result.setScoreSecond(reviewDto.getScoreSecond());
		result.setScoreThird(reviewDto.getScoreThird());
		result.setContent(reviewDto.getContent());

		return true;
	}

	@Override
	@Transactional
	public void removeReviewAsUser(Long seq) throws PermissionDeniedException {
		Optional<Review> review = reviewRepository.findById(seq);
		if (!review.get().getUser().getSeq().equals(AuthUtil.getAuthenticationInfoSeq())) {
			throw new PermissionDeniedException();
		}
		reviewRepository.deleteById(seq);
	}

	@Override
	@Transactional
	public void removeReviewAsAdmin(Long seq) {
		reviewRepository.deleteById(seq);
	}

	@Override
	@Transactional
	public void likeReview(Long userSeq, Long reviewSeq) {
		ReviewLike reviewLike = ReviewLike.builder()
			.review(Review.builder().seq(reviewSeq).build())
			.user(User.builder().seq(userSeq).build())
			.build();
		reviewLikeRepository.save(reviewLike);
	}

	@Override
	@Transactional
	public void dislikeReview(Long userSeq, Long reviewSeq) {
		ReviewLikeId reviewLikeId = ReviewLikeId.builder()
			.review(reviewSeq)
			.user(userSeq)
			.build();
		reviewLikeRepository.deleteById(reviewLikeId);
	}

	@Override
	public DetailReviewScoreVo getDetailReview(Long webtoonSeq) throws IOException {
		DetailReviewScoreVo reviewScoreVo = new DetailReviewScoreVo();
		for (String path : paths) {
			InputStream inputStream = new ClassPathResource("static/" + path).getInputStream();
			String data = new BufferedReader(new InputStreamReader(inputStream)).lines()
				.collect(Collectors.joining("\n"));
			inputStream.close();
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(data);
			double score = 0.0;
			try {
				Double.parseDouble(element.getAsJsonObject()
					.get(Long.toString(webtoonSeq))
					.getAsJsonObject()
					.get("score_mean")
					.getAsString());
			} catch (NullPointerException ignored) {
				score = 0.0;
			}
			reviewScoreVo.addData(path.split("\\.")[0], score);
		}
		return reviewScoreVo;
	}
}
