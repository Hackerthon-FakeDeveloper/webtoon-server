package org.corodiak.scfakedeveloper.handler;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.scfakedeveloper.auth.exception.UnAuthorizeException;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "org.corodiak.scfakedeveloper.controller")
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler({
		NoSuchElementException.class,
		MissingServletRequestParameterException.class
	})
	public ResponseModel parameterError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.message("요청에 필요한 파라미터가 비어있거나 적절하지 않습니다.")
			.build();
		response.setStatus(400);
		return responseModel;
	}

	@ExceptionHandler(UnAuthorizeException.class)
	public ResponseModel unAuthorizeRequestError(HttpServletRequest request, HttpServletResponse response,
		Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.UNAUTHORIZED)
			.message("인증되지 않은 사용자입니다.")
			.build();
		response.setStatus(401);
		return responseModel;
	}

	@ExceptionHandler({
		SearchResultNotExistException.class
	})
	public ResponseModel searchResultNotExistError(HttpServletRequest request, HttpServletResponse response,
		Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.NOT_FOUND)
			.message("검색 결과가 존재하지 않습니다.")
			.build();
		response.setStatus(404);
		return responseModel;
	}

	@ExceptionHandler(Exception.class)
	public ResponseModel unExceptedError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("UnExcepted Error occurred. : {}", e.toString());
		response.setStatus(500);
		return ResponseModel.builder()
			.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
			.message("예기치 못한 오류 발생")
			.build();
	}
}