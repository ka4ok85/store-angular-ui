package com.example.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {
	@JsonProperty
	private String login;

	@JsonProperty
	private String password;
	
	@JsonProperty
	private Long store;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Login [login=" + login + ", password=" + password + ", store=" + store + "]";
	}
}
