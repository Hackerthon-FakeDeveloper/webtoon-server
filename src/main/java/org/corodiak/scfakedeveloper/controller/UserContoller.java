package org.corodiak.scfakedeveloper.controller;

import java.util.List;

import org.corodiak.scfakedeveloper.auth.util.AuthUtil;
import org.corodiak.scfakedeveloper.exception.NotAllowValueException;
import org.corodiak.scfakedeveloper.service.UserService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.UserDto;
import org.corodiak.scfakedeveloper.type.vo.UserVo;
import org.corodiak.scfakedeveloper.type.vo.ViewHistoryVo;
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

	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel userGet(
		@PathVariable("seq") Long seq
	) {
		UserVo user = userService.findUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("user", user);
		return responseModel;
	}

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

	// 해당 로직 Admin으로 핸들링 할 것
	// @RequestMapping(method = RequestMethod.PUT)
	// public ResponseModel userUpdate(
	// 	@RequestBody UserDto userDto
	// ) {
	// 	userService.updateUser(userDto);
	// 	ResponseModel responseModel = ResponseModel.builder().build();
	// 	return responseModel;
	// }

	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel userDelete(
		@PathVariable("seq") Long seq
	) {
		userService.removeUser(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/viewHistory/{webtoonSeq}", method = RequestMethod.POST)
	public ResponseModel viewHistoryAdd(
		@PathVariable("webtoonSeq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		userService.addViewHistory(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/viewHistory/list", method = RequestMethod.GET)
	public ResponseModel viewHistryList() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<ViewHistoryVo> viewHistoryList = userService.findViewHistroy(userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("viewHistoryList", viewHistoryList);
		return responseModel;
	}

	@RequestMapping(value = "/viewHistory/{webtoonSeq}", method = RequestMethod.DELETE)
	public ResponseModel viewHistoryRemove(
		@PathVariable("webtoonSeq") Long webtoonSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		userService.removeViewHistory(userSeq, webtoonSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/checkinfoset", method = RequestMethod.GET)
	public ResponseModel checkUserInfo() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("check", userService.userInfoIsSet(userSeq));
		return responseModel;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseModel userGet() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("user", userService.findUser(userSeq));
		return responseModel;
	}

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
}
