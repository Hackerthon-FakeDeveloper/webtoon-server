package org.corodiak.scfakedeveloper.auth.service;

import java.util.Optional;

import org.corodiak.scfakedeveloper.repository.UserRepository;
import org.corodiak.scfakedeveloper.type.dto.UserPrincipal;
import org.corodiak.scfakedeveloper.type.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findById(Long.parseLong(username));
		log.debug("User Data : {}", user);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return UserPrincipal.create(user.get());
	}
}
