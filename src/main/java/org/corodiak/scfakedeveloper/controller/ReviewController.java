package org.corodiak.scfakedeveloper.controller;

import java.io.IOException;
import java.util.List;

import org.corodiak.scfakedeveloper.auth.util.AuthUtil;
import org.corodiak.scfakedeveloper.exception.PermissionDeniedException;
import org.corodiak.scfakedeveloper.service.ReviewService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.ReviewDto;
import org.corodiak.scfakedeveloper.type.vo.ReviewVo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

	private final ReviewService reviewService;

	@Operation(summary = "리뷰 추가", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel reviewAdd(
		@RequestBody ReviewDto reviewDto
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		reviewDto.setUser(userSeq);
		reviewService.addReview(reviewDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "리뷰 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel reviewGet(
		@PathVariable("seq") Long seq
	) {
		ReviewVo review = reviewService.findReview(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("review", review);
		return responseModel;
	}

	@Operation(summary = "내 리뷰 가져오기", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/myReview", method = RequestMethod.GET)
	public ResponseModel myReviewList(
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<ReviewVo> reviewList = reviewService.findByUserSeq(userSeq, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("reviewList", reviewList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰의 리뷰 불러오기", description = "PERMIT_ALL")
	@RequestMapping(value = "/webtoonReview/{seq}", method = RequestMethod.GET)
	public ResponseModel webtoonReviewList(
		@PathVariable("seq") Long seq,
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<ReviewVo> reviewList = reviewService.findByWebtoonSeq(seq, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("reviewList", reviewList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "전체 리뷰 개수 가져오기", description = "PERMIT_ALL")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseModel reviewCount() {
		Long count = reviewService.getReviewCount();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("count", count);
		return responseModel;
	}

	@Operation(summary = "리뷰 업데이트", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseModel reviewUpdate(
		@RequestBody ReviewDto reviewDto
	) throws PermissionDeniedException {
		if (!reviewDto.getUser().equals(AuthUtil.getAuthenticationInfoSeq())) {
			throw new PermissionDeniedException();
		}

		reviewService.updateReview(reviewDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "리뷰 삭제(일반 유저)", description = "ROLE_USER")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/user/{seq}", method = RequestMethod.DELETE)
	public ResponseModel reviewDeleteAsUser(
		@PathVariable("seq") Long seq
	) throws PermissionDeniedException {
		reviewService.removeReviewAsUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "리뷰 삭제(관리자)", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/admin/{seq}", method = RequestMethod.DELETE)
	public ResponseModel reviewDeleteAsAdmin(
		@PathVariable("seq") Long seq
	) {
		reviewService.removeReviewAsAdmin(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "리뷰 좋아요", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/like/{seq}", method = RequestMethod.POST)
	public ResponseModel likReview(
		@PathVariable("seq") Long reviewSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		reviewService.likeReview(userSeq, reviewSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "리뷰 좋아요 취소", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/dislike/{seq}", method = RequestMethod.POST)
	public ResponseModel dislikReview(
		@PathVariable("seq") Long reviewSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		reviewService.dislikeReview(userSeq, reviewSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/detailscore/{webtoonSeq}")
	public ResponseModel detailReviewScore(
		@PathVariable("webtoonSeq") Long webtoonSeq
	) throws IOException {
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("detailReviewScore", reviewService.getDetailReview(webtoonSeq).getDetailReview());
		return responseModel;
	}
}
