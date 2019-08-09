package com.brubank.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Representation of a flight reservation")
public class FlightReservation {
	@Id
	@ApiModelProperty(value = "The identifier")
	private UUID reservationId;
	
	@NotNull
	@ApiModelProperty(value = "Destination")
	private String destination;
	
	@NotNull
	@ApiModelProperty(value = "Date of flight")
	private LocalDateTime date;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public UUID getReservationId() {
		return reservationId;
	}

	public void setReservationId(UUID reservationId) {
		this.reservationId = reservationId;
	}

}
