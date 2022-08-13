package org.corodiak.scfakedeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.service.TagService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.TagDto;
import org.corodiak.scfakedeveloper.type.vo.TagVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseModel tagAdd(
            @RequestBody TagDto tagDto
    ) {
        tagService.addTag(tagDto);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseModel tagGet (
            @PathVariable("seq") Long seq
    ) {
        TagVo tag = tagService.findTag(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("tag", tag);
        return responseModel;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseModel tagList() {
        List<TagVo> tagList = tagService.findAll();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("tagList", tagList);
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseModel tagDelete(
            @PathVariable("seq") Long seq
    ) {
        tagService.removeTag(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}
