package com.brubank.service;

import java.util.List;

import com.brubank.domain.Venue;

public interface VenueService {
	List<Venue> findHotels(String city);
}
