package com.brubank.service;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.brubank.domain.Venue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class VenueServiceTest {

	@Autowired
	private VenueService sut;

	@Test
	public void givenDestinationExisting_thenReturnsHotels() {
		// Given
		String city = "Rome";

		// When
		List<Venue> result = sut.findHotels(city);

		// Then
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(greaterThanOrEqualTo(0)));
	}

	@Test
	public void givenDestinationEmpty_thenReturnsEmptyList() {
		// Given
		String city = "";

		// When
		List<Venue> result = sut.findHotels(city);

		// Then
		assertThat(result, is(not(nullValue())));
		assertThat(result, is((emptyCollectionOf(Venue.class))));
	}

}
