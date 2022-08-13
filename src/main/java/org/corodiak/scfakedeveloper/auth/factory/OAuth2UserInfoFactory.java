package org.corodiak.scfakedeveloper.auth.factory;

import java.util.Map;

import org.corodiak.scfakedeveloper.type.dto.GoogleOAuthUserInfo;
import org.corodiak.scfakedeveloper.type.dto.OAuthUserInfo;
import org.corodiak.scfakedeveloper.type.etc.OAuthProvider;

public class OAuth2UserInfoFactory {
	public static OAuthUserInfo of(OAuthProvider oAuthProvider, Map<String, Object> attributes) throws IllegalArgumentException {
		switch (oAuthProvider) {
			case GOOGLE:
				return new GoogleOAuthUserInfo(attributes);
			default:
				throw new IllegalArgumentException("OAuthProvider Not Excepted!!");
		}
	}
}
