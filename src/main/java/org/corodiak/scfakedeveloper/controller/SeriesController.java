package org.corodiak.scfakedeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.service.SeriesService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.vo.SeriesVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseModel SeriesAdd(
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        seriesService.addSeries(title, description);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseModel seriesGet(
            @PathVariable("seq") Long seq
    ) {
        SeriesVo series = seriesService.findSeries(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("series", series);
        return responseModel;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseModel seriesList() {
        List<SeriesVo> seriesList = seriesService.findAll();
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("seriesList", seriesList);
        return responseModel;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseModel search(
            @RequestParam("keyword") String keyword
    ) {
        List<SeriesVo> seriesList = seriesService.search(keyword);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData("seriesList", seriesList);
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseModel seriesUpdate(
            @PathVariable("seq") Long seq,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        seriesService.updateSeries(seq, title, description);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseModel seriesDelete(
            @PathVariable("seq") Long seq
    ) {
        seriesService.removeSeries(seq);
        ResponseModel responseModel = ResponseModel.builder().build();
        return responseModel;
    }
}
