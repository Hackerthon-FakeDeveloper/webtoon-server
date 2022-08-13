package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.*;

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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
