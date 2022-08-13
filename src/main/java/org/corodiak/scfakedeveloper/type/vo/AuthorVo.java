package org.corodiak.scfakedeveloper.type.vo;

import org.corodiak.scfakedeveloper.type.entity.Author;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthorVo {

	private Long seq;
	private String name;
	private String description;

	public AuthorVo(Author entity) {
		this.seq = entity.getSeq();
		this.name = entity.getName();
		this.description = entity.getDescription();
	}
}
