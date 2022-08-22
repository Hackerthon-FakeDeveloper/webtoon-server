package org.corodiak.scfakedeveloper.controller;

import java.util.List;

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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel genreList() {
		List<GenreVo> genreList = genreService.findAll();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("genreList", genreList);
		return responseModel;
	}

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
