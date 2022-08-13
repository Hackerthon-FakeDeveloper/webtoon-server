package org.corodiak.scfakedeveloper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.AuthorRepository;
import org.corodiak.scfakedeveloper.type.dto.AuthorDto;
import org.corodiak.scfakedeveloper.type.entity.Author;
import org.corodiak.scfakedeveloper.type.vo.AuthorVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	@Override
	@Transactional
	public boolean addAuthor(AuthorDto authorDto) {
		Author author = Author.builder()
			.name(authorDto.getName())
			.description(authorDto.getDescription())
			.build();
		authorRepository.save(author);
		return true;
	}

	@Override
	@Transactional
	public List<AuthorVo> findAll() {
		List<Author> authorList = authorRepository.findAll();
		List<AuthorVo> results = authorList.stream()
			.map(e -> new AuthorVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public AuthorVo findAuthor(Long seq) {
		Optional<Author> author = authorRepository.findById(seq);
		if (author.isPresent()) {
			return new AuthorVo(author.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public void removeAuthor(Long seq) {
		authorRepository.deleteById(seq);
	}

	@Override
	@Transactional
	public boolean updateAuthor(AuthorDto authorDto) {
		Optional<Author> author = authorRepository.findById(authorDto.getSeq());
		if (!author.isPresent()) {
			throw new SearchResultNotExistException();
		}

		Author result = author.get();
		result.setName(authorDto.getName());
		result.setDescription(authorDto.getDescription());

		return true;
	}

	@Override
	@Transactional
	public List<AuthorVo> searchAuthor(String keyword) {
		List<Author> authorList = authorRepository.search(keyword);
		List<AuthorVo> results = authorList.stream()
			.map(e -> new AuthorVo(e))
			.collect(Collectors.toList());
		return results;
	}

}
