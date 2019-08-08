package com.brubank.travel.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.brubank.travel.domain.VenueSearch;
import com.brubank.travel.domain.Venue;

import reactor.core.publisher.Mono;

@Service
@CacheConfig(cacheNames = { "hotels" })
public class VenueServiceImpl implements VenueService {

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

		return Arrays.asList(mono.block().getResponse().getVenues());
	}
}
