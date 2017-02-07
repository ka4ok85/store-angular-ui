package com.example.dto.location;

import com.example.dto.productlocation.Productlocation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationProductlocationWrapper {

	@JsonProperty
	private Location storelocation;
	
	@JsonProperty
	private Productlocation productlocation;

	public Location getStorelocation() {
		return storelocation;
	}

	public void setStorelocation(Location storelocation) {
		this.storelocation = storelocation;
	}

	public Productlocation getProductlocation() {
		return productlocation;
	}

	public void setProductlocation(Productlocation productlocation) {
		this.productlocation = productlocation;
	}

	@Override
	public String toString() {
		return "LocationProductlocationWrapper [storelocation=" + storelocation + ", productlocation=" + productlocation + "]";
	}
	
	
}
