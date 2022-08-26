package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import org.corodiak.scfakedeveloper.auth.util.AuthUtil;
import org.corodiak.scfakedeveloper.service.WebtoonService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.WebtoonDto;
import org.corodiak.scfakedeveloper.type.dto.WebtoonSeriesDto;
import org.corodiak.scfakedeveloper.type.dto.WebtoonTagDto;
import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;
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
@RequestMapping("/webtoon")
public class WebtoonController {

	private final WebtoonService webtoonService;

	@Operation(summary = "웹툰 정보 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel webtoonAdd(
		@RequestBody WebtoonDto webtoonDto
	) {
		webtoonService.addWebtoon(webtoonDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 정보 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel webtoonGet(
		@PathVariable("seq") Long seq
	) {
		WebtoonVo webtoon = webtoonService.findWebtoon(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoon", webtoon);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "특정 작가의 작품 불러오기", description = "PERMIT_ALL")
	@RequestMapping(value = "/authorWebtoon/{seq}", method = RequestMethod.GET)
	public ResponseModel authorWebtoonList(
		@PathVariable("seq") Long seq
	) {
		List<WebtoonVo> webtoonList = webtoonService.findByAuthorSeq(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "특정 플랫폼의 웹툰 불러오기", description = "PERMIT_ALL")
	@RequestMapping(value = "/platformWebtoon", method = RequestMethod.GET)
	public ResponseModel platformWebtoonList(
		@RequestParam("platform") String platform,
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<WebtoonVo> webtoonList = webtoonService.findByPlatform(platform, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 정보 불러오기", description = "PERMIT_ALL")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel webtoonList(
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<WebtoonVo> webtoonList = webtoonService.findAll(start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 제목 기반 검색", description = "PERMIT_ALL")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseModel search(
		@RequestParam("keyword") String keyword,
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<WebtoonVo> webtoonList = webtoonService.search(keyword, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	@Operation(summary = "웹툰 정보 업데이트", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseModel webtoonUpdate(
		@RequestBody WebtoonDto webtoonDto
	) {
		webtoonService.updateWebtoon(webtoonDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "웹툰 정보 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel webtoonDelete(
		@PathVariable("seq") Long seq
	) {
		webtoonService.removeWebtoon(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "웹툰 좋아요", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/like/{seq}", method = RequestMethod.POST)
	public ResponseModel likeWebtoon(
		@PathVariable("seq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		webtoonService.likeWebtoon(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "웹툰 좋아요 취소", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/dislike/{seq}", method = RequestMethod.POST)
	public ResponseModel dislikeWebtoon(
		@PathVariable("seq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		webtoonService.dislikeWebtoon(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "내가 좋아요 한 웹툰 불러오기", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public ResponseModel searchByLike(
			@RequestParam(name = "start", required = false, defaultValue = "0") long start,
			@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<WebtoonVo> webtoonList = webtoonService.findByLike(userSeq, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	@Operation(summary = "웹툰 시리즈 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/series", method = RequestMethod.POST)
	public ResponseModel webtoonSeriesAdd(
		@RequestBody WebtoonSeriesDto webtoonSeriesDto
	) {
		webtoonService.addWebtoonSeries(webtoonSeriesDto.getWebtoon(), webtoonSeriesDto.getSeries());
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "웹툰 시리즈 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/series", method = RequestMethod.DELETE)
	public ResponseModel webtoonSeriesRemove(
		@RequestBody WebtoonSeriesDto webtoonSeriesDto
	) {
		webtoonService.removeWebtoonSeries(webtoonSeriesDto.getWebtoon(), webtoonSeriesDto.getSeries());
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 시리즈 기반 검색", description = "PERMIT_ALL")
	@RequestMapping(value = "/series/{seq}", method = RequestMethod.GET)
	public ResponseModel searchBySeries(
		@PathVariable("seq") Long seriesSeq
	) {
		List<WebtoonVo> webtoonList = webtoonService.findBySeries(seriesSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	@Operation(summary = "웹툰 태그 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public ResponseModel webtoonTagAdd(
		@RequestBody WebtoonTagDto webtoonTagDto
	) {
		webtoonService.addWebtoonTag(webtoonTagDto.getWebtoon(), webtoonTagDto.getTag());
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "웹툰 태그 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/tag", method = RequestMethod.DELETE)
	public ResponseModel webtoonTagRemove(
		@RequestBody WebtoonTagDto webtoonTagDto
	) {
		webtoonService.removeWebtoonTag(webtoonTagDto.getWebtoon(), webtoonTagDto.getTag());
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 태그 기반 검색(시퀀스)", description = "PERMIT_ALL")
	@RequestMapping(value = "/tag/seq/{seq}", method = RequestMethod.GET)
	public ResponseModel searchByTagSeq(
			@RequestParam(name = "start", required = false, defaultValue = "0") long start,
			@RequestParam(name = "display", required = false, defaultValue = "20") long display,
		@PathVariable("seq") Long tagSeq
	) {
		List<WebtoonVo> webtoonList = webtoonService.findByTagSeq(tagSeq, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 태그 기반 검색(문자열)", description = "PERMIT_ALL")
	@RequestMapping(value = "/tag/string/{tag}", method = RequestMethod.GET)
	public ResponseModel searchByTagString(
			@RequestParam(name = "start", required = false, defaultValue = "0") long start,
			@RequestParam(name = "display", required = false, defaultValue = "20") long display,
			@PathVariable("tag") String tag
	) {
		List<WebtoonVo> webtoonList = webtoonService.findByTagString(tag, start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "최신 웹툰 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ResponseModel newWebtoon(
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<WebtoonVo> webtoonList = webtoonService.findNewWebtoon(start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "인기 웹툰 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/popular/all", method = RequestMethod.GET)
	public ResponseModel popularWebtoon(
			@RequestParam(name = "start", required = false, defaultValue = "0") long start,
			@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<WebtoonVo> webtoonList = webtoonService.findPopularWebtoon(start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "인기 웹툰 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/popular/recent", method = RequestMethod.GET)
	public ResponseModel recentPopularWebtoon(
			@RequestParam(name = "start", required = false, defaultValue = "0") long start,
			@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<WebtoonVo> webtoonList = webtoonService.findRecentPopularWebtoon(start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	@RequestMapping(value = "/relate/{seq}", method = RequestMethod.GET)
	public ResponseModel relatedWebtoon(
		@PathVariable("seq") Long webtoonSeq
	) {
		List<WebtoonVo> results = webtoonService.findRelatedWebtoon(webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", results);
		return responseModel;
	}
}
