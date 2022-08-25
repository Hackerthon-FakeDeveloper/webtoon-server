package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.corodiak.scfakedeveloper.service.SeriesService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.SeriesDto;
import org.corodiak.scfakedeveloper.type.vo.SeriesVo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

	private final SeriesService seriesService;

	@Operation(summary = "시리즈 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel SeriesAdd(
		@RequestBody SeriesDto seriesDto
	) {
		seriesService.addSeries(seriesDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "시리즈 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel seriesGet(
		@PathVariable("seq") Long seq
	) {
		SeriesVo series = seriesService.findSeries(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("series", series);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 기반 시리즈 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/webtoon/{webtoonSeq}", method = RequestMethod.GET)
	public ResponseModel getByWebtoon(
			@PathVariable("webtoonSeq") Long webtoonSeq
	) {
		SeriesVo series = seriesService.findByWebtoonSeq(webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("series", series);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "시리즈 목록 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel seriesList() {
		List<SeriesVo> seriesList = seriesService.findAll();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("seriesList", seriesList);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "시리즈 검색", description = "PERMIT_ALL")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseModel search(
		@RequestParam("keyword") String keyword
	) {
		List<SeriesVo> seriesList = seriesService.search(keyword);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("seriesList", seriesList);
		return responseModel;
	}

	@Operation(summary = "시리즈 업데이트", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseModel seriesUpdate(
		@RequestBody SeriesDto seriesDto
	) {
		seriesService.updateSeries(seriesDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "시리즈 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel seriesDelete(
		@PathVariable("seq") Long seq
	) {
		seriesService.removeSeries(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}
}
