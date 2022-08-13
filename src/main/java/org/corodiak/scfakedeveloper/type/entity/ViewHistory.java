package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.scfakedeveloper.type.entity.id.ViewHistoryId;

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
@IdClass(ViewHistoryId.class)
public class ViewHistory extends BaseTimeEntity {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private User user;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_seq")
	private Webtoon webtoon;
}