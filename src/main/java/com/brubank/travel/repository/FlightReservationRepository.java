package com.brubank.travel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brubank.travel.domain.FlightReservation;

@Repository
public interface FlightReservationRepository extends CrudRepository<FlightReservation, UUID> {
	List<FlightReservation> findByDestinationContainingOrderByDateAsc(String city);
}
