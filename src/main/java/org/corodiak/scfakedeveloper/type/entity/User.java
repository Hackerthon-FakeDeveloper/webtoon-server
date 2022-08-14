package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.corodiak.scfakedeveloper.type.etc.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "users")
@Setter
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long seq;

	@Column
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column
	private Role role;

	@Builder
	public User(Long seq, String nickname, Role role) {
		this.seq = seq;
		this.nickname = nickname;
		this.role = role;
	}
}
