package com.example.dto.location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationPageContainer<T> {
	@JsonProperty
	private int size;
	
	@JsonProperty
	private int totalElements;
	
	@JsonProperty
	private List<T> content;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	@Override
	public String toString() {
		return "LocationPageContainer [size=" + size + ", totalElements=" + totalElements + ", content=" + content
				+ "]";
	}

}
