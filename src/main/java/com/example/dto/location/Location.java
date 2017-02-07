package com.example.dto.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
	@JsonProperty
	private Long id;

	@JsonProperty
	private Long storeId;
	
	@JsonProperty
	private Long shelf;
	
	@JsonProperty
	private Long slot;
	
	@JsonProperty
	private String barcode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getShelf() {
		return shelf;
	}

	public void setShelf(Long shelf) {
		this.shelf = shelf;
	}

	public Long getSlot() {
		return slot;
	}

	public void setSlot(Long slot) {
		this.slot = slot;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", storeId=" + storeId + ", shelf=" + shelf + ", slot=" + slot + ", barcode="
				+ barcode + "]";
	}
}
