package com.brubank.travel.service;

import java.util.List;

import com.brubank.travel.domain.Venue;

public interface VenueService {
	List<Venue> findHotels(String city);
}
