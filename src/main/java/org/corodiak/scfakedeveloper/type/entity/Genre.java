package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_seq")
	private Long seq;
	private String genre;

	@Builder
	public Genre(Long seq, String genre) {
		this.seq = seq;
		this.genre = genre;
	}
}
