package org.corodiak.scfakedeveloper.config;

import java.io.OutputStream;

import org.corodiak.scfakedeveloper.auth.filter.TokenAuthFilter;
import org.corodiak.scfakedeveloper.auth.handler.OAuth2AuthenticationFailureHandler;
import org.corodiak.scfakedeveloper.auth.handler.OAuth2AuthenticationSuccessHandler;
import org.corodiak.scfakedeveloper.auth.jwt.AuthTokenProvider;
import org.corodiak.scfakedeveloper.auth.service.CustomOAuth2UserService;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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

	/*
	HttpSecurity 의 sessionManagement()는 Exception을 발생시킨다.
	상세 타입이 아니라 선언 자체가 Exception.class를 발생시킨다.
	 */
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

		//Rest API 이기에, Stateless라 CSRF 방어 불필요
		http.csrf().disable();

		http.addFilterBefore(tokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling()
			.authenticationEntryPoint((request, response, authException) -> {
				ResponseModel responseModel = ResponseModel.builder()
					.httpStatus(HttpStatus.UNAUTHORIZED)
					.message("인증되지 않은 사용자이거나 권한이 부족합니다.")
					.build();
				response.setStatus(401);
				response.setContentType("application/json");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(responseModel.toJson().getBytes());
			});
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
