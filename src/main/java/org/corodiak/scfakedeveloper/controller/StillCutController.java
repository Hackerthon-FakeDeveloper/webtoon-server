package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import org.corodiak.scfakedeveloper.service.StillCutService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.StillCutDto;
import org.corodiak.scfakedeveloper.type.vo.StillCutVo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stillCut")
public class StillCutController {

	private final StillCutService stillCutService;

	@Operation(summary = "스틸컷 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel stillCutAdd(
		@RequestBody StillCutDto stillCutDto
	) {
		stillCutService.addStillCut(stillCutDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "스틸컷 단일 조회", description = "PERMIT_ALL 아마 쓸일 없을듯?")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel stillCutGet(
		@PathVariable("seq") Long seq
	) {
		StillCutVo stillCut = stillCutService.findStillCut(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("stillCut", stillCut);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "웹툰 스틸컷 불러오기", description = "PERMIT_ALL")
	@RequestMapping(value = "/webtoonStillCut/{seq}", method = RequestMethod.GET)
	public ResponseModel webtoonStillCutList(
		@PathVariable("seq") Long seq
	) {
		List<StillCutVo> stillCutList = stillCutService.findByWebtoonSeq(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("stillCutList", stillCutList);
		return responseModel;
	}

	@Operation(summary = "스틸컷 수정", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseModel stillCutUpdate(
		@RequestBody StillCutDto stillCutDto
	) {
		stillCutService.updateStillCut(stillCutDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "스틸컷 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel stillCutDelete(
		@PathVariable("seq") Long seq
	) {
		stillCutService.removeStillCut(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}
}
