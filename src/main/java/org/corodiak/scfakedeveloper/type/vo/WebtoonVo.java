package org.corodiak.scfakedeveloper.type.vo;

import java.time.LocalDate;

import org.corodiak.scfakedeveloper.type.entity.Webtoon;
import org.corodiak.scfakedeveloper.type.etc.SerialStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WebtoonVo {

	private Long seq;
	private String title;
	private String platform;
	private boolean isAdult;
	private String thumbnail;
	private String url;
	private String description;
	private SerialStatus serialStatus;
	private double scoreFirstAverage;
	private double scoreSecondAverage;
	private double scoreThirdAverage;
	private double scoreTotalAverage;
	private LocalDate startDate;

	public WebtoonVo(Webtoon entity) {
		this.seq = entity.getSeq();
		this.title = entity.getTitle();
		this.platform = entity.getPlatform();
		this.isAdult = entity.isAdult();
		this.thumbnail = entity.getThumbnail();
		this.url = entity.getUrl();
		this.description = entity.getDescription();
		this.serialStatus = entity.getSerialStatus();
		this.scoreFirstAverage = entity.getScoreFirstAverage();
		this.scoreSecondAverage = entity.getScoreSecondAverage();
		this.scoreThirdAverage = entity.getScoreThirdAverage();
		this.scoreTotalAverage = entity.getScoreTotalAverage();
		this.startDate = entity.getStartDate();
	}

	@Getter
	@ToString(callSuper = true)
	public static class WebtoonVoWithAuthor extends WebtoonVo {

		private AuthorVo author;

		public WebtoonVoWithAuthor(Webtoon entity) {
			super(entity);
			this.author = new AuthorVo(entity.getAuthor());
		}
	}

}
