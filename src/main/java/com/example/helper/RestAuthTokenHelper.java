package com.example.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestAuthTokenHelper {
	public static HttpEntity<?> getHttpEntity (String authToken) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", authToken);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);

        return httpEntity;
	}

	public static HttpEntity<String> getPostHttpEntity (String json, String authToken) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", authToken);
        HttpEntity<String> httpEntity = new HttpEntity<String>(json, requestHeaders);

        return httpEntity;
	}
}
