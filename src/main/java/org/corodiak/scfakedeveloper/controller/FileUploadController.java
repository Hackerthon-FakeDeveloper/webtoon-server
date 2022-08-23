package org.corodiak.scfakedeveloper.controller;

import org.corodiak.scfakedeveloper.exception.FileUploadFailException;
import org.corodiak.scfakedeveloper.service.FileUploadService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class FileUploadController {

	private final FileUploadService fileUploadService;

	@Operation(summary = "파일 업로드", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	private ResponseModel upload(
		@RequestParam(value = "file", required = true) MultipartFile multipartFile
	) throws FileUploadFailException {
		String url = fileUploadService.saveFile(multipartFile);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("url", url);
		return responseModel;
	}

}
