package org.corodiak.scfakedeveloper.service;

import lombok.RequiredArgsConstructor;
import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.StillCutRepository;
import org.corodiak.scfakedeveloper.type.dto.StillCutDto;
import org.corodiak.scfakedeveloper.type.entity.StillCut;
import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.vo.StillCutVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StillCutServiceImpl implements StillCutService {

    private final StillCutRepository stillCutRepository;

    @Override
    @Transactional
    public boolean addStillCut(StillCutDto stillCutDto) {
        StillCut stillCut = StillCut.builder()
                .url(stillCutDto.getUrl())
                .description(stillCutDto.getDescription())
                .webtoon(Webtoon.builder().seq(stillCutDto.getWebtoon()).build())
                .build();
        stillCutRepository.save(stillCut);
        return true;
    }

    @Override
    @Transactional
    public StillCutVo findStillCut(Long seq) {
        Optional<StillCut> stillCut = stillCutRepository.findById(seq);
        if (stillCut.isPresent()) {
            return new StillCutVo.StillCutVoWithWebtoon(stillCut.get());
        }
        throw new SearchResultNotExistException();
    }

    @Override
    @Transactional
    public List<StillCutVo> findByWebtoonSeq(Long seq) {
        List<StillCut> stillCutList = stillCutRepository.findByWebtoonSeq(seq);
        List<StillCutVo> results = stillCutList.stream()
                .map(e -> new StillCutVo.StillCutVoWithWebtoon(e))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public boolean updateStillCut(StillCutDto stillCutDto) {
        Optional<StillCut> stillCut = stillCutRepository.findById(stillCutDto.getSeq());
        if (!stillCut.isPresent()) {
            throw new SearchResultNotExistException();
        }

        StillCut result = stillCut.get();
        result.setUrl(stillCutDto.getUrl());
        result.setDescription(stillCutDto.getDescription());

        return true;
    }

    @Override
    @Transactional
    public void removeStillCut(Long seq) {
        stillCutRepository.deleteById(seq);
    }
}
