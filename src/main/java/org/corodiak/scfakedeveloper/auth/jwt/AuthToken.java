package org.corodiak.scfakedeveloper.auth.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthToken {

	@Getter
	private final String token;

	private final Key key;

	AuthToken(String id, String role, Date expiry, Key key) {
		this.key = key;
		token = createToken(id, role, expiry);
	}

	private String createToken(String id, String role, Date expiry) {
		return Jwts.builder()
			.setHeaderParam("typ", "JWT")
			.setSubject("token")
			.claim("id", id)
			.claim("role", role)
			.setIssuedAt(new Date())
			.setExpiration(expiry)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean validate() {
		return this.getClaims() != null;
	}

	public Claims getClaims() {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}