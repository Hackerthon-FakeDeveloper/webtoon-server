package org.corodiak.scfakedeveloper.type.vo;

import org.corodiak.scfakedeveloper.type.entity.Series;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SeriesVo {

	private Long seq;
	private String title;
	private String description;

	public SeriesVo(Series entity) {
		this.seq = entity.getSeq();
		this.title = entity.getTitle();
		this.description = entity.getDescription();
	}
}
