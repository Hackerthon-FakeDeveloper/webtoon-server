package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.corodiak.scfakedeveloper.service.TagService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.TagDto;
import org.corodiak.scfakedeveloper.type.vo.TagVo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

	private final TagService tagService;

	@Operation(summary = "태그 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel tagAdd(
		@RequestBody TagDto tagDto
	) {
		tagService.addTag(tagDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "태그 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel tagGet(
		@PathVariable("seq") Long seq
	) {
		TagVo tag = tagService.findTag(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("tag", tag);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "태그 목록 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel tagList() {
		List<TagVo> tagList = tagService.findAll();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("tagList", tagList);
		return responseModel;
	}

	@Operation(summary = "태그 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel tagDelete(
		@PathVariable("seq") Long seq
	) {
		tagService.removeTag(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}
}
