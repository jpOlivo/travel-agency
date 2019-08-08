package com.brubank.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.brubank.travel.domain.FlightReservation;
import com.brubank.travel.domain.Venue;
import com.brubank.travel.dto.RecommendationDTO;
import com.brubank.travel.service.FlightReservationService;
import com.brubank.travel.service.VenueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("/v1/recommendations")
@Validated
public class RecommendationController {
	@Autowired
	private FlightReservationService flightReservationService;

	@Autowired
	private VenueService hotelService;

	@GetMapping("/v1/recommendations/{city}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = RecommendationDTO.class) })
	@ApiOperation(value = "Return a list of flight reservations order by date and a list of hotels for the city specified", response = RecommendationDTO.class)
	public ResponseEntity<?> getHotelRecommendations(
			@ApiParam(required = true, name = "city", value = "Name of destination city") @PathVariable String city) {
		List<FlightReservation> flightReservations = flightReservationService.getFlightReservations(city);
		List<Venue> hotels = hotelService.findHotels(city);

		return ResponseEntity.ok().body(new RecommendationDTO(flightReservations, hotels));

	}

}
