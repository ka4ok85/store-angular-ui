package com.example.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginBackend {
	@JsonProperty
	private String username;

	@JsonProperty
	private String password;
	
	@JsonProperty
	private Long storeId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "LoginBackend [username=" + username + ", password=" + password + ", storeId=" + storeId + "]";
	}
	

}
