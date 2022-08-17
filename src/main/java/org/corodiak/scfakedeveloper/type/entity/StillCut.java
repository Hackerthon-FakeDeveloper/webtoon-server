package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class StillCut extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stillcut_seq")
	private Long seq;
	private String url;
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_seq")
	private Webtoon webtoon;

	@Builder
	public StillCut(Long seq, String url, String description, Webtoon webtoon) {
		this.seq = seq;
		this.url = url;
		this.description = description;
		this.webtoon = webtoon;
	}
}
