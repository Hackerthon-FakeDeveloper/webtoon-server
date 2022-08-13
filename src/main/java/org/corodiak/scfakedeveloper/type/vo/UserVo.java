package org.corodiak.scfakedeveloper.type.vo;

import org.corodiak.scfakedeveloper.type.entity.User;
import org.corodiak.scfakedeveloper.type.etc.Role;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserVo {

	private Long seq;
	private String nickname;
	private Role role;

	public UserVo(User entity) {
		this.seq = entity.getSeq();
		this.nickname = entity.getNickname();
		this.role = entity.getRole();
	}
}
