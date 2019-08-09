package com.brubank.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.brubank.domain.Venue;
import com.brubank.domain.VenueSearch;

import reactor.core.publisher.Mono;

@Service
@CacheConfig(cacheNames = { "hotels" })
public class VenueServiceImpl implements VenueService {
	private static final Logger LOGGER = LoggerFactory.getLogger(VenueServiceImpl.class);

	@Value("${api.foursquare.client.id}")
	private String clientId;

	@Value("${api.foursquare.client.secret}")
	private String clientSecret;

	@Override
	@Cacheable
	public List<Venue> findHotels(String city) {
		WebClient client = WebClient.create("https://api.foursquare.com/v2/venues");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		Mono<VenueSearch> mono = client.get()
				.uri(uriBuilder -> uriBuilder.path("/search").queryParam("near", "{city}")
						.queryParam("intent", "browse").queryParam("query", "hotel")
						.queryParam("client_id", "{client_id}").queryParam("client_secret", "{client_secret}")
						.queryParam("v", formatter.format(LocalDate.now()).toString())
						.build(city, clientId, clientSecret))
				.retrieve().bodyToMono(VenueSearch.class);

		Venue[] venues = new Venue[] {};
		try {
			venues = mono.block().getResponse().getVenues();
		} catch (Exception e) {
			LOGGER.warn("There was not found hotels for destination: " + city);
		}
		
		return Arrays.asList(venues);
	}
}
