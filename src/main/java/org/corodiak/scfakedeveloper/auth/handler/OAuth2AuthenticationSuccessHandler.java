package org.corodiak.scfakedeveloper.auth.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.scfakedeveloper.auth.jwt.AuthToken;
import org.corodiak.scfakedeveloper.auth.jwt.AuthTokenProvider;
import org.corodiak.scfakedeveloper.type.dto.ResponseModel;
import org.corodiak.scfakedeveloper.type.dto.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final long TOKEN_DURATION = 1000L * 60L * 60L * 24L * 365L;

	private final AuthTokenProvider authTokenProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

		String role = authentication.getAuthorities().stream()
			.map(a -> ((GrantedAuthority)a).getAuthority())
			.collect(Collectors.joining("|"));

		Date expiry = new Date();
		expiry.setTime(expiry.getTime() + (TOKEN_DURATION));
		AuthToken authToken = authTokenProvider.createToken(Long.toString(userPrincipal.getUserId()), role, expiry);
		log.debug("AuthToken Data : {}", authToken.getToken());
		ResponseModel responseModel = ResponseModel.builder()
			.message("Authorization Token Issued.")
			.build();
		response.setHeader("Authorization", authToken.getToken());
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(responseModel.toJson().getBytes());
	}
}
