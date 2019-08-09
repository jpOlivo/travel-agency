package com.brubank.controller;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.brubank.domain.FlightReservation;
import com.brubank.domain.Venue;
import com.brubank.dto.RecommendationDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RecommendationControllerIT {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void givenDestinationExisting_thenReturnsReservationsAndHotels() {
		// Given
		String destination = "Paris";

		// When
		ResponseEntity<RecommendationDTO> response = restTemplate.getForEntity("/v1/recommendations/{city}",
				RecommendationDTO.class, destination);

		// Then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		RecommendationDTO result = response.getBody();
		assertThat(result.getHotels().size(), is(greaterThanOrEqualTo(0)));
		assertThat(result.getFlightReservations().size(), is(greaterThanOrEqualTo(0)));
	}
	
	@Test
	public void givenDestinationNonExisting_thenReturnsEmptyLists() throws InterruptedException {
		// Given
		String destination = "askpakdqzte";
		
		// When
		ResponseEntity<RecommendationDTO> response = restTemplate.getForEntity("/v1/recommendations/{city}",
				RecommendationDTO.class, destination);

		// Then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		RecommendationDTO result = response.getBody();
		assertThat(result.getHotels(), is((emptyCollectionOf(Venue.class))));
		assertThat(result.getFlightReservations(), is(emptyCollectionOf(FlightReservation.class)));
	}

	
}
