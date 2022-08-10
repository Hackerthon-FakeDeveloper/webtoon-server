package org.corodiak.scfakedeveloper.type.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
public class Webtoon extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webtoon_seq")
	private Long seq;
	private String title;
	private String platform;
	private boolean isAdult;
	private String thumbnail;
	private String url;
	private String description;
	private LocalDate startDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_seq")
	private Author author;
	//Genre 분리

	@Builder
	public Webtoon(Long seq, String title, String platform, boolean isAdult, String thumbnail,
				   String url, String description, LocalDate startDate, Author author) {
		this.seq = seq;
		this.title = title;
		this.platform = platform;
		this.isAdult = isAdult;
		this.thumbnail = thumbnail;
		this.url = url;
		this.description = description;
		this.startDate = startDate;
		this.author = author;
	}
}
