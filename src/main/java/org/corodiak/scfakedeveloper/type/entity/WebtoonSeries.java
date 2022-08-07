package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.scfakedeveloper.type.entity.id.WebtoonSeriesId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@IdClass(WebtoonSeriesId.class)
public class WebtoonSeries {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_seq")
	private Webtoon webtoon;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_seq")
	private Series series;
}
