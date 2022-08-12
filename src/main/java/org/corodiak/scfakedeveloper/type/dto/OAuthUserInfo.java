package org.corodiak.scfakedeveloper.type.dto;

import java.util.HashMap;
import java.util.Map;

public abstract class OAuthUserInfo {

	protected Map<String, Object> attributes;

	public OAuthUserInfo(Map<String, Object> attributes) {
		this.attributes = new HashMap<>(attributes);
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public abstract String getId();

	public abstract String getOAuthProviderName();

	public abstract String getEmail();

	public abstract String getName();

}
