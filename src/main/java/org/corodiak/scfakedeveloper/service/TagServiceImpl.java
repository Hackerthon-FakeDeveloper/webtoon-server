package org.corodiak.scfakedeveloper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.TagRepository;
import org.corodiak.scfakedeveloper.type.dto.TagDto;
import org.corodiak.scfakedeveloper.type.entity.Tag;
import org.corodiak.scfakedeveloper.type.vo.TagVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;

	@Override
	@Transactional
	public boolean addTag(TagDto tagDto) {
		Tag tag = Tag.builder()
			.name(tagDto.getName())
			.build();
		tagRepository.save(tag);
		return true;
	}

	@Override
	@Transactional
	public TagVo findTag(Long seq) throws SearchResultNotExistException {
		Optional<Tag> tag = tagRepository.findById(seq);
		if (tag.isPresent()) {
			return new TagVo(tag.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public List<TagVo> findAll() {
		List<Tag> tagList = tagRepository.findAll();
		List<TagVo> results = tagList.stream()
			.map(e -> new TagVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void removeTag(Long seq) {
		tagRepository.deleteById(seq);
	}
}
