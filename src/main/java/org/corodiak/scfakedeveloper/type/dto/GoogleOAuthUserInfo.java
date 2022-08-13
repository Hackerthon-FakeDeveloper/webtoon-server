package org.corodiak.scfakedeveloper.type.dto;

import java.util.Map;

public class GoogleOAuthUserInfo extends OAuthUserInfo {

	public GoogleOAuthUserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		Object sub = attributes.get("sub");
		if(sub instanceof String) {
			return (String)attributes.get("sub");
		}
		throw new NullPointerException("Sub is null.");
	}

	@Override
	public String getOAuthProviderName() {
		return "google";
	}

	@Override
	public String getEmail() {
		Object email = attributes.get("email");
		if(email instanceof String) {
			return (String)email;
		}
		throw new NullPointerException("Email is null.");
	}

	@Override
	public String getName() {
		Object name = attributes.get("name");
		if(name instanceof String) {
			return (String)name;
		}
		throw new NullPointerException("Name is null.");
	}

	@Override
	public String toString() {
		return "GoogleOAuthUserInfo{" +
			"attributes=" + attributes +
			'}';
	}

}
