package org.corodiak.scfakedeveloper.config;

import org.corodiak.scfakedeveloper.auth.filter.TokenAuthFilter;
import org.corodiak.scfakedeveloper.auth.handler.OAuth2AuthenticationFailureHandler;
import org.corodiak.scfakedeveloper.auth.handler.OAuth2AuthenticationSuccessHandler;
import org.corodiak.scfakedeveloper.auth.jwt.AuthTokenProvider;
import org.corodiak.scfakedeveloper.auth.service.CustomOAuth2UserService;
import org.corodiak.scfakedeveloper.auth.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final AuthTokenProvider authTokenProvider;

	private static final String[] PERMIT_ALL = {
		"/login/**",
		"/v2/api-docs",
		"/swagger-resources",
		"/swagger-resources/**",
		"/configuration/ui",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**",
		"/v3/api-docs/**",
		"/swagger-ui/**"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.oauth2Login()
			.userInfoEndpoint()
			.userService(customOAuth2UserService)
			.and()
			.successHandler(oAuth2AuthenticationSuccessHandler())
			.failureHandler(oAuth2AuthenticationFailureHandler())
			.and()
			.authorizeHttpRequests()
			.antMatchers(PERMIT_ALL).permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/*").hasAnyRole("USER", "ADMIN");
		
		//임시 설정
		http.csrf().disable();
		http.addFilterBefore(tokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
		return new OAuth2AuthenticationSuccessHandler(authTokenProvider);
	}

	@Bean
	public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
		return new OAuth2AuthenticationFailureHandler();
	}

	@Bean
	public TokenAuthFilter tokenAuthFilter() {
		return new TokenAuthFilter(authTokenProvider);
	}

}
