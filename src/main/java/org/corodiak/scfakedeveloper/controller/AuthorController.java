package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import org.corodiak.scfakedeveloper.service.AuthorService;
import org.corodiak.scfakedeveloper.type.dto.AuthorDto;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.vo.AuthorVo;
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
@RequestMapping("/author")
public class AuthorController {

	private final AuthorService authorService;

	@Operation(summary = "작가 정보 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel authorAdd(
		@RequestBody AuthorDto authorDto
	) {
		authorService.addAuthor(authorDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "단일 작가 정보 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel authorGet(
		@PathVariable("seq") Long seq
	) {
		AuthorVo author = authorService.findAuthor(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("author", author);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "다수 작가 정보 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel authorList() {
		List<AuthorVo> authorList = authorService.findAll();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("authorList", authorList);
		return responseModel;
	}

	@Operation(summary = "작가 정보 업데이트", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseModel authorUpdate(
		@RequestBody AuthorDto authorDto
	) {
		authorService.updateAuthor(authorDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "작가 정보 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel authorDelete(
		@PathVariable("seq") Long seq
	) {
		authorService.removeAuthor(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "작가 검색", description = "PERMIT_ALL")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseModel authorSearch(
		@RequestParam("keyword") String keyword
	) {
		List<AuthorVo> authorList = authorService.searchAuthor(keyword);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("authorList", authorList);
		return responseModel;
	}
}
