package org.corodiak.scfakedeveloper.type.dto;

import java.util.Map;

public class GoogleOAuthUserInfo extends OAuthUserInfo {

	public GoogleOAuthUserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String)attributes.get("sub");
	}

	@Override
	public String getOAuthProviderName() {
		return "google";
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}

	@Override
	public String toString() {
		return "GoogleOAuthUserInfo{" +
			"attributes=" + attributes +
			'}';
	}

}
