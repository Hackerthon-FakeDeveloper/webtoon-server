package org.corodiak.scfakedeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.auth.util.AuthUtil;
import org.corodiak.scfakedeveloper.service.ReviewService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.vo.ReviewVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseModel reviewAdd(
        @RequestParam("scoreFirst") int scoreFirst,
        @RequestParam("scoreSecond") int scoreSecond,
        @RequestParam("scoreThird") int scoreThird,
        @RequestParam("content") String content,
        @RequestParam("webtoonSeq") Long webtoonSeq
    ) {
        Long userSeq = AuthUtil.getAuthenticationInfoSeq();
        reviewService.addReview(scoreFirst, scoreSecond, scoreThird, content,
                userSeq, webtoonSeq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseModel reviewGet(
            @PathVariable("seq") Long seq
    ) {
        ReviewVo review = reviewService.findReview(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("review", review);
        return responseModel;
    }

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

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseModel reviewUpdate(
            @PathVariable("seq") Long seq,
            @RequestParam("scoreFirst") int scoreFirst,
            @RequestParam("scoreSecond") int scoreSecond,
            @RequestParam("scoreThird") int scoreThird,
            @RequestParam("content") String content
    ) {
        reviewService.updateReview(seq, scoreFirst, scoreSecond, scoreThird, content);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseModel reviewDelete(
            @PathVariable("seq") Long seq
    ) {
        reviewService.removeReview(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}
