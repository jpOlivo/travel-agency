package com.brubank.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.brubank.domain.FlightReservation;
import com.brubank.repository.FlightReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class FlightReservationServiceTest {
	@MockBean
	private FlightReservationRepository repository;
	
	@Autowired
	private FlightReservationService sut;

	@Test
	public void givenDestinationExisting_thenReturnsReservationList() {
		// Given
		String city = "Seoul";
		
		FlightReservation flightReservation = new FlightReservation();
		flightReservation.setReservationId(UUID.randomUUID());
		flightReservation.setDestination(city);
		flightReservation.setDate(LocalDateTime.now());

		List<FlightReservation> flightReservations = new ArrayList<FlightReservation>();
		flightReservations.add(flightReservation);
		doReturn(flightReservations).when(repository).findByDestinationContainingOrderByDateAsc(city);

		// When
		List<FlightReservation> result = sut.getFlightReservations(city);

		// Then
		assertThat(result.get(0), is(not(nullValue())));
		assertThat(result.get(0).getDestination(), is(city));

		ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
		verify(repository).findByDestinationContainingOrderByDateAsc(usernameCaptor.capture());
		assertThat(usernameCaptor.getValue(), is(city));
		
	}
}
