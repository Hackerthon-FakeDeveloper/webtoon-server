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

	@Operation(summary = "웹툰 좋아요", description = "ROLE_USER | ROLE_ADMIN")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/like/{seq}", method = RequestMethod.POST)
	public ResponseModel likeWebtoon(
		@PathVariable("seq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		webtoonService.likeWebtoon(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "웹툰 좋아요 취소", description = "ROLE_USER | ROLE_ADMIN")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/dislike/{seq}", method = RequestMethod.POST)
	public ResponseModel dislikeWebtoon(
		@PathVariable("seq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		webtoonService.dislikeWebtoon(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "내가 좋아요 한 웹툰 불러오기", description = "ROLE_USER | ROLE_ADMIN")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public ResponseModel searchByLike() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<WebtoonVo> webtoonList = webtoonService.findByLike(userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/series", method = RequestMethod.POST)
	public ResponseModel webtoonSeriesAdd(
		@RequestBody WebtoonSeriesDto webtoonSeriesDto
	) {
		webtoonService.addWebtoonSeries(webtoonSeriesDto.getWebtoon(), webtoonSeriesDto.getSeries());
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

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
	@RequestMapping(value = "/series/{seq}", method = RequestMethod.GET)
	public ResponseModel searchBySeries(
		@PathVariable("seq") Long seriesSeq
	) {
		List<WebtoonVo> webtoonList = webtoonService.findBySeries(seriesSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public ResponseModel webtoonTagAdd(
		@RequestBody WebtoonTagDto webtoonTagDto
	) {
		webtoonService.addWebtoonTag(webtoonTagDto.getWebtoon(), webtoonTagDto.getTag());
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

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
	@RequestMapping(value = "/tag/{seq}", method = RequestMethod.GET)
	public ResponseModel searchByTag(
		@PathVariable("seq") Long tagSeq
	) {
		List<WebtoonVo> webtoonList = webtoonService.findByTag(tagSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("webtoonList", webtoonList);
		return responseModel;
	}
}
