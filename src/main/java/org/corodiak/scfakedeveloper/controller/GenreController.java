package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.corodiak.scfakedeveloper.service.GenreService;
import org.corodiak.scfakedeveloper.type.dto.GenreDto;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.vo.GenreVo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

	private final GenreService genreService;

	@Operation(summary = "장르 추가", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel genreAdd(
		@RequestBody GenreDto genreDto
	) {
		genreService.addGenre(genreDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	// Permit All
	@Operation(summary = "장르 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel genreGet(
		@PathVariable("seq") Long seq
	) {
		GenreVo genre = genreService.findGenre(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("genre", genre);
		return responseModel;
	}

	// Permit All
	@Operation(summary = "장르 목록 조회", description = "PERMIT_ALL")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel genreList() {
		List<GenreVo> genreList = genreService.findAll();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("genreList", genreList);
		return responseModel;
	}

	@Operation(summary = "장르 삭제", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel genreDelete(
		@PathVariable("seq") Long seq
	) {
		genreService.removeGenre(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}
}
