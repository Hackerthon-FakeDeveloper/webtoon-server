package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.scfakedeveloper.type.entity.id.WebtoonTagId;

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
@IdClass(WebtoonTagId.class)
public class WebtoonTag {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_seq")
	private Webtoon webtoon;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_seq")
	private Tag tag;
}
