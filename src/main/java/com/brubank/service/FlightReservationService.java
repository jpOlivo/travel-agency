package com.brubank.service;

import java.util.List;

import com.brubank.domain.FlightReservation;

public interface FlightReservationService {
	
	void populateFlightReservationsCache();

	List<FlightReservation> getFlightReservations(String city);
}
