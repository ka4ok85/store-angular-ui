package com.example.dto.productlocation;

import com.example.dto.location.Location;
import com.example.dto.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Productlocation {

	@JsonProperty
	private Long id;
	
	@JsonProperty
	private Product product;
	
	@JsonProperty
	private Long storeId;

	@JsonProperty
	private Location storelocation;

	@JsonProperty
	private Long quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getStore() {
		return storeId;
	}

	public void setStore(Long storeId) {
		this.storeId = storeId;
	}

	public Location getStorelocation() {
		return storelocation;
	}

	public void setStorelocation(Location storelocation) {
		this.storelocation = storelocation;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Productlocation [id=" + id + ", product=" + product + ", storeId=" + storeId + ", storelocation=" + storelocation
				+ ", quantity=" + quantity + "]";
	}
	
}
