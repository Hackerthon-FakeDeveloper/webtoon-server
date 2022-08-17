package org.corodiak.scfakedeveloper.type.entity.id;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewHistoryId implements Serializable {
	private Long user;
	private Long webtoon;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ViewHistoryId that = (ViewHistoryId)o;
		return Objects.equals(user, that.user) && Objects.equals(webtoon, that.webtoon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, webtoon);
	}
}
