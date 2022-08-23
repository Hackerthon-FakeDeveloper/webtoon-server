package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.corodiak.scfakedeveloper.auth.util.AuthUtil;
import org.corodiak.scfakedeveloper.exception.NotAllowValueException;
import org.corodiak.scfakedeveloper.exception.PermissionDeniedException;
import org.corodiak.scfakedeveloper.service.UserService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.UserDto;
import org.corodiak.scfakedeveloper.type.vo.UserVo;
import org.corodiak.scfakedeveloper.type.vo.ViewHistoryVo;
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
@RequestMapping("/user")
public class UserContoller {

	private final UserService userService;

	@Operation(summary = "유저 정보 조회(일반 유저)", description = "ROLE_USER")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/user/{seq}", method = RequestMethod.GET)
	public ResponseModel userGetAsUser(
			@PathVariable("seq") Long seq
	) throws PermissionDeniedException {
		if (!seq.equals(AuthUtil.getAuthenticationInfoSeq())) {
			throw new PermissionDeniedException();
		}

		UserVo user = userService.findUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("user", user);
		return responseModel;
	}

	@Operation(summary = "유저 정보 조회(관리자)", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/admin/{seq}", method = RequestMethod.GET)
	public ResponseModel userGetAsAdmin(
		@PathVariable("seq") Long seq
	) {
		UserVo user = userService.findUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("user", user);
		return responseModel;
	}

	@Operation(summary = "유저 정보 리스트 조회", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel userList(
		@RequestParam(name = "start", required = false, defaultValue = "0") long start,
		@RequestParam(name = "display", required = false, defaultValue = "20") long display
	) {
		List<UserVo> userList = userService.findAll(start, display);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("userList", userList);
		return responseModel;
	}

	@Operation(summary = "유저 업데이트", description = "ROLE_USER")
	@Secured({"ROLE_USER"})
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseModel userUpdate(
			@RequestBody UserDto userDto
	) throws NotAllowValueException {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		userDto.setSeq(userSeq);
		userService.updateUser(userDto);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "유저 삭제(일반 유저)", description = "ROLE_USER")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/user/{seq}", method = RequestMethod.DELETE)
	public ResponseModel userDeleteAsUser(
		@PathVariable("seq") Long seq
	) throws PermissionDeniedException {
		if (!seq.equals(AuthUtil.getAuthenticationInfoSeq())) {
			throw new PermissionDeniedException();
		}

		userService.removeUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "유저 삭제(관리자)", description = "ROLE_ADMIN")
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/admin/{seq}", method = RequestMethod.DELETE)
	public ResponseModel userDeleteAsAdmin(
			@PathVariable("seq") Long seq
	) {
		userService.removeUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "기록 추가", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/viewHistory/{webtoonSeq}", method = RequestMethod.POST)
	public ResponseModel viewHistoryAdd(
		@PathVariable("webtoonSeq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		userService.addViewHistory(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "내 기록 조회", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/viewHistory/list", method = RequestMethod.GET)
	public ResponseModel viewHistroyList() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<ViewHistoryVo> viewHistoryList = userService.findViewHistory(userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("viewHistoryList", viewHistoryList);
		return responseModel;
	}

	@Operation(summary = "기록 삭제", description = "ROLE_USER / ROLE_ADMIN")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/viewHistory/{webtoonSeq}", method = RequestMethod.DELETE)
	public ResponseModel viewHistoryRemove(
		@PathVariable("webtoonSeq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		userService.removeViewHistory(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@Operation(summary = "유저 정보 업데이트 여부 체크", description = "ROLE_USER")
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/checkinfoset", method = RequestMethod.GET)
	public ResponseModel checkUserInfo() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("check", userService.userInfoIsSet(userSeq));
		return responseModel;
	}
}
