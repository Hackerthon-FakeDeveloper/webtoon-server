package org.corodiak.scfakedeveloper.type.entity.id;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebtoonTagId implements Serializable {
	private Long webtoon;
	private Long tag;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WebtoonTagId that = (WebtoonTagId)o;
		return Objects.equals(webtoon, that.webtoon) && Objects.equals(tag, that.tag);
	}

	@Override
	public int hashCode() {
		return Objects.hash(webtoon, tag);
	}
}
