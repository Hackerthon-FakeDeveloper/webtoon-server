package org.corodiak.scfakedeveloper.type.vo;

import org.corodiak.scfakedeveloper.type.entity.Tag;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TagVo {
	private Long seq;
	private String name;

	public TagVo(Tag entity) {
		this.seq = entity.getSeq();
		this.name = entity.getName();
	}
}
