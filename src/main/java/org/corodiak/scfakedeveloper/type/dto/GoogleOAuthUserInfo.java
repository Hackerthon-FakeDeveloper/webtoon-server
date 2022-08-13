package org.corodiak.scfakedeveloper.type.dto;

import java.util.Map;

import org.corodiak.scfakedeveloper.auth.exception.UnAuthorizeException;

public class GoogleOAuthUserInfo extends OAuthUserInfo {

	public GoogleOAuthUserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() throws UnAuthorizeException {
		Object sub = attributes.get("sub");
		if(sub instanceof String) {
			return (String)attributes.get("sub");
		}
		throw new UnAuthorizeException("Sub is null.");
	}

	@Override
	public String getOAuthProviderName() {
		return "google";
	}

	@Override
	public String getEmail() throws UnAuthorizeException {
		Object email = attributes.get("email");
		if(email instanceof String) {
			return (String)email;
		}
		throw new UnAuthorizeException("Email is null.");
	}

	@Override
	public String getName() throws UnAuthorizeException {
		Object name = attributes.get("name");
		if(name instanceof String) {
			return (String)name;
		}
		throw new UnAuthorizeException("Name is null.");
	}

	@Override
	public String toString() {
		return "GoogleOAuthUserInfo{" +
			"attributes=" + attributes +
			'}';
	}

}
