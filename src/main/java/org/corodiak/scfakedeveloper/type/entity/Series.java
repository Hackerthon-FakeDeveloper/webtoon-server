package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class Series extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "series_seq")
	private Long seq;
	private String title;
	private String description;

	@Builder
	public Series(Long seq, String title, String description) {
		this.seq = seq;
		this.title = title;
		this.description = description;
	}
}
