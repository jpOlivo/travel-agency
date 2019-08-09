package com.brubank.dto;

import java.util.List;

import com.brubank.domain.FlightReservation;
import com.brubank.domain.Venue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representation of a recommendation")
public class RecommendationDTO {
	@ApiModelProperty(value = "List of flight reservations")
	private List<FlightReservation> flightReservations;

	@ApiModelProperty(value = "List of hotels")
	private List<Venue> hotels;

	public RecommendationDTO(List<FlightReservation> flightReservations, List<Venue> hoteles) {
		super();
		this.flightReservations = flightReservations;
		this.hotels = hoteles;
	}

	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}

	public List<Venue> getHotels() {
		return hotels;
	}

	public void setHotels(List<Venue> hotels) {
		this.hotels = hotels;
	}

}
