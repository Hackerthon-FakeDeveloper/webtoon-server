package org.corodiak.scfakedeveloper.type.entity;

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
@Setter
public class Review extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_seq")
	private Long seq;
	private int scoreFirst;
	private int scoreSecond;
	private int scoreThird;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_seq")
	private Webtoon webtoon;

	@Builder
	public Review(Long seq, int scoreFirst, int scoreSecond, int scoreThird,
				  String content, User user, Webtoon webtoon) {
		this.seq = seq;
		this.scoreFirst = scoreFirst;
		this.scoreSecond = scoreSecond;
		this.scoreThird = scoreThird;
		this.content = content;
		this.user = user;
		this.webtoon = webtoon;
	}
}
