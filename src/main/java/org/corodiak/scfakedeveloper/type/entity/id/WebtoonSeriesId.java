package org.corodiak.scfakedeveloper.type.entity.id;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebtoonSeriesId implements Serializable {
	private Long webtoon;
	private Long series;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WebtoonSeriesId that = (WebtoonSeriesId)o;
		return Objects.equals(webtoon, that.webtoon) && Objects.equals(series, that.series);
	}

	@Override
	public int hashCode() {
		return Objects.hash(webtoon, series);
	}
}
