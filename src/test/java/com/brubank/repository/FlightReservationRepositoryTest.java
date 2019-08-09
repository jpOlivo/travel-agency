package com.brubank.repository;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.brubank.domain.FlightReservation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class FlightReservationRepositoryTest {

	@Autowired
	private FlightReservationRepository sut;

	@Test
	public void givenDestination_thenReturnsReservationsForIt() {
		// Given
		String city = "Berlin";

		// When
		List<FlightReservation> result = sut.findByDestinationContainingOrderByDateAsc(city);

		// Then
		for (FlightReservation flightReservation : result) {
			assertThat(flightReservation.getDestination(), containsString(city));
		}

	}

}
