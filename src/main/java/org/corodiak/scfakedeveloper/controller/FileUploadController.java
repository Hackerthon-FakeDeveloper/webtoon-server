package org.corodiak.scfakedeveloper.controller;

import javax.mail.MessagingException;

import org.corodiak.scfakedeveloper.exception.FileUploadFailException;
import org.corodiak.scfakedeveloper.service.EmailService;
import org.corodiak.scfakedeveloper.service.FileUploadService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileUploadController {

	private final FileUploadService fileUploadService;
	private final EmailService emailService;

	@Operation(summary = "파일 업로드", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseModel upload(
		@RequestParam(value = "file", required = true) MultipartFile multipartFile
	) throws FileUploadFailException {
		String url = fileUploadService.saveFile(multipartFile);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("url", url);
		return responseModel;
	}

	@RequestMapping(value = "/upload/capture", method = RequestMethod.POST)
	public ResponseModel uploadCapture(
		@RequestParam(value = "file", required = true) MultipartFile multipartFile
	) throws FileUploadFailException, MessagingException {
		emailService.sendMail(multipartFile);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}
}
