package org.corodiak.scfakedeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.service.UserService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.UserDto;
import org.corodiak.scfakedeveloper.type.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseModel userUpdate(
            @RequestBody UserDto userDto
    ) {
        userService.updateUser(userDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseModel userDelete(
            @PathVariable("seq") Long seq
    ) {
        userService.removeUser(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}
