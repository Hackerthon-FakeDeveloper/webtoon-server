package org.corodiak.scfakedeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.service.AuthorService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.vo.AuthorVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseModel authorAdd(
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ) {
        authorService.addAuthor(name, description);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseModel authorGet(
            @PathVariable("seq") Long seq
    ) {
        AuthorVo author = authorService.findAuthor(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("author", author);
        return responseModel;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseModel authorList() {
        List<AuthorVo> authorList = authorService.findAll();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("authorList", authorList);
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseModel authorUpdate(
            @PathVariable("seq") Long seq,
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ) {
        authorService.updateAuthor(seq, name, description);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseModel authorDelete(
            @PathVariable("seq") Long seq
    ) {
        authorService.removeAuthor(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

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