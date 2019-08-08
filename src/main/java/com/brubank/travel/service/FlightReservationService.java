package com.brubank.travel.service;

import java.util.List;

import com.brubank.travel.domain.FlightReservation;

public interface FlightReservationService {
	
	void populateFlightReservationsCache();

	List<FlightReservation> getFlightReservations(String city);
}
