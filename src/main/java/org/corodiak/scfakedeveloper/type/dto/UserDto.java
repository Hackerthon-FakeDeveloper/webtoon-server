package org.corodiak.scfakedeveloper.type.dto;

import org.corodiak.scfakedeveloper.type.etc.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Long seq;
	private String nickname;
	private Role role;
}
