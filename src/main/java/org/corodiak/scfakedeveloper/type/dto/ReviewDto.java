package org.corodiak.scfakedeveloper.type.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	private Long seq;
	private int scoreFirst;
	private int scoreSecond;
	private int scoreThird;
	private String content;
	private Long user;
	private Long webtoon;
}
