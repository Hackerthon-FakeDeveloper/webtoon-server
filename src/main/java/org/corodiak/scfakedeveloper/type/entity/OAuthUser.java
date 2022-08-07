package org.corodiak.scfakedeveloper.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.corodiak.scfakedeveloper.type.etc.OAuthProvider;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OAuthUser extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oauthuser_seq")
	private Long seq;

	@Column(
		unique = true,
		nullable = false,
		length = 100
	)
	private String providerUserId;

	@Column(
		unique = true,
		nullable = false,
		length = 100
	)
	private String email;

	@Column(
		nullable = false
	)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column
	private OAuthProvider oap;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_seq")
	private User user;

	@Builder
	public OAuthUser(Long seq, String providerUserId, String email, String name,
		OAuthProvider oap, User user) {
		this.seq = seq;
		this.providerUserId = providerUserId;
		this.email = email;
		this.name = name;
		this.oap = oap;
		this.user = user;
	}
}
