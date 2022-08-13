package org.corodiak.scfakedeveloper.type.dto;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.corodiak.scfakedeveloper.type.entity.User;
import org.corodiak.scfakedeveloper.type.etc.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {

	private final Long userId;
	private final String nickname;
	private final Role role;
	private final Collection<GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	@Override
	public String getPassword() {
		return "[PROTECTED]";
	}

	@Override
	public String getUsername() {
		return Long.toString(userId);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object> clone = new HashMap<>();
		for (String key : attributes.keySet()) {
			Object value = attributes.get(key);
			clone.put(key, value);
		}
		return clone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>(authorities);
	}

	@Override
	public String getName() {
		return "[" + userId + " : " + nickname + "]";
	}

	public static UserPrincipal create(User user) {
		return new UserPrincipal(user.getSeq(), user.getNickname(), user.getRole(),
			Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getKey())));
	}
}
