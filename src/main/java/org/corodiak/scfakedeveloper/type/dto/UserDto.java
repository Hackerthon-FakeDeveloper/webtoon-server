package org.corodiak.scfakedeveloper.type.dto;

import lombok.Getter;
import lombok.Setter;
import org.corodiak.scfakedeveloper.type.etc.Role;

@Getter
@Setter
public class UserDto {
    private Long seq;
    private String nickname;
    private Role role;
}
