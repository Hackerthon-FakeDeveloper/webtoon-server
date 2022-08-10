package org.corodiak.scfakedeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.service.WebtoonService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.vo.WebtoonVo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebtoonController {

    private final WebtoonService webtoonService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseModel webtoonAdd(
            @RequestParam("title") String title,
            @RequestParam("platform") String platform,
            @RequestParam("isAdult") boolean isAdult,
            @RequestParam("thumbnail") String thumbnail,
            @RequestParam("url") String url,
            @RequestParam("description") String description,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("authorSeq") Long authorSeq
    ) {
        webtoonService.addWebtoon(title, platform, isAdult, thumbnail, url,
                description, startDate, authorSeq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseModel webtoonGet(
            @PathVariable("seq") Long seq
    ) {
        WebtoonVo webtoon = webtoonService.findWebtoon(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("webtoon", webtoon);
        return responseModel;
    }

    @RequestMapping(value = "/authorWebtoon/{seq}", method = RequestMethod.GET)
    public ResponseModel authorWebtoonList(
            @PathVariable("seq") Long seq
    ) {
        List<WebtoonVo> webtoonList = webtoonService.findByAuthorSeq(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("webtoonList", webtoonList);
        return responseModel;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseModel webtoonList(
            @RequestParam(name = "start", required = false, defaultValue = "0") long start,
            @RequestParam(name = "display", required = false, defaultValue = "20") long display
    ) {
        List<WebtoonVo> webtoonList = webtoonService.findAll(start, display);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("webtoonList", webtoonList);
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseModel webtoonUpdate(
            @PathVariable("seq") Long seq,
            @RequestParam("title") String title,
            @RequestParam("platform") String platform,
            @RequestParam("isAdult") boolean isAdult,
            @RequestParam("thumbnail") String thumbnail,
            @RequestParam("url") String url,
            @RequestParam("description") String description,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("authorSeq") Long authorSeq
    ) {
        webtoonService.updateWebtoon(seq, title, platform, isAdult, thumbnail, url, description, startDate, authorSeq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseModel webtoonDelete(
            @PathVariable("seq") Long seq
    ) {
        webtoonService.removeWebtoon(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}
