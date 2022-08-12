package org.corodiak.scfakedeveloper.type.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonDto {
	private Long seq;
	private String title;
	private String platform;
	private boolean isAdult;
	private String thumbnail;
	private String url;
	private String description;
	private LocalDate startDate;
	private String author;
}
