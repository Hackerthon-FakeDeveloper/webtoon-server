package org.corodiak.scfakedeveloper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.corodiak.scfakedeveloper.exception.SearchResultNotExistException;
import org.corodiak.scfakedeveloper.repository.GenreRepository;
import org.corodiak.scfakedeveloper.type.dto.GenreDto;
import org.corodiak.scfakedeveloper.type.entity.Genre;
import org.corodiak.scfakedeveloper.type.vo.GenreVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;

	@Override
	@Transactional
	public boolean addGenre(GenreDto genreDto) {
		Genre entity = Genre.builder()
			.genre(genreDto.getGenre())
			.build();
		genreRepository.save(entity);
		return true;
	}

	@Override
	@Transactional
	public GenreVo findGenre(Long seq) throws SearchResultNotExistException {
		Optional<Genre> genre = genreRepository.findById(seq);
		if (genre.isPresent()) {
			return new GenreVo(genre.get());
		}
		throw new SearchResultNotExistException();
	}

	@Override
	@Transactional
	public List<GenreVo> findAll() {
		List<Genre> genreList = genreRepository.findAll();
		List<GenreVo> results = genreList.stream()
			.map(e -> new GenreVo(e))
			.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void removeGenre(Long seq) {
		genreRepository.deleteById(seq);
	}
}
