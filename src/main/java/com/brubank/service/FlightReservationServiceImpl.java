package com.brubank.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.brubank.domain.FlightReservation;
import com.brubank.repository.FlightReservationRepository;

import reactor.core.publisher.Flux;

@Service
public class FlightReservationServiceImpl implements FlightReservationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightReservationServiceImpl.class);

	@Autowired
	private FlightReservationRepository flightReservationRepository;

	@Scheduled(fixedRate = 480000) // time in millis
	@Override
	public void populateFlightReservationsCache() {
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info("Populating flight reservations cache...");
		}
		
		WebClient client = WebClient.create("https://brubank-flights.herokuapp.com");

		Flux<FlightReservation> flux = client.get().uri("/flight-reservations").retrieve()
				.bodyToFlux(FlightReservation.class);
		flux.subscribe(fr -> flightReservationRepository.save(fr),
					   e  -> LOGGER.warn("Cache not populated, retrying in some minutes..."));
	}

	@Override
	public List<FlightReservation> getFlightReservations(String city) {
		return flightReservationRepository.findByDestinationContainingOrderByDateAsc(city);
	}
}
