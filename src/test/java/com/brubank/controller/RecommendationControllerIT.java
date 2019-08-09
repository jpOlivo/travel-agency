package com.brubank.controller;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.greaterThan;
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
	public void givenDestination_whenExistReservations_thenReturnsReservations() {
		// Given
		String destination = "Hyderabad";

		// When
		ResponseEntity<RecommendationDTO> response = restTemplate.getForEntity("/v1/recommendations/{city}",
				RecommendationDTO.class, destination);

		// Then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		RecommendationDTO result = response.getBody();
		assertThat(result.getFlightReservations().size(), is(greaterThan(0)));
	}

	@Test
	public void givenDestination_whenExistHotels_thenReturnsHotels() {
		// Given
		String destination = "Hyderabad";

		// When
		ResponseEntity<RecommendationDTO> response = restTemplate.getForEntity("/v1/recommendations/{city}",
				RecommendationDTO.class, destination);

		// Then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		RecommendationDTO result = response.getBody();
		assertThat(result.getFlightReservations().size(), is(greaterThan(0)));
	}

	@Test
	public void givenDestination_whenNonExistHotels_thenReturnsEmptyList() throws InterruptedException {
		// Given
		String destination = "asdasdkeres";

		// When
		ResponseEntity<RecommendationDTO> response = restTemplate.getForEntity("/v1/recommendations/{city}",
				RecommendationDTO.class, destination);

		// Then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		RecommendationDTO result = response.getBody();
		assertThat(result.getHotels(), is(emptyCollectionOf(Venue.class)));
	}

	@Test
	public void givenDestination_whenNonExistReservations_thenReturnsEmptyList() throws InterruptedException {
		// Given
		String destination = "Buenos Aires";

		// When
		ResponseEntity<RecommendationDTO> response = restTemplate.getForEntity("/v1/recommendations/{city}",
				RecommendationDTO.class, destination);

		// Then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		RecommendationDTO result = response.getBody();
		assertThat(result.getFlightReservations(), is(emptyCollectionOf(FlightReservation.class)));
	}

}
