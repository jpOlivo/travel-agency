package com.brubank.travel.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HotelLocation", description = "Representation of location")
public class VenueLocation {
	@ApiModelProperty(value = "Location address")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
