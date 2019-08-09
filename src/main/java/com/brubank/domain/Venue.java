package com.brubank.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Hotel", description = "Representation of a hotel")
public class Venue {
	@ApiModelProperty(value = "The name")
	private String name;
	
	@ApiModelProperty(value = "The location")
	private VenueLocation location;

	public String getName() {
		return name;
	}

	public VenueLocation getLocation() {
		return location;
	}

	public void setLocation(VenueLocation location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

}
