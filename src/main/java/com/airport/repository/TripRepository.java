package com.airport.repository;

import com.airport.model.Trip;
import com.airport.repository.common.CommonRepository;

import java.util.List;

public interface TripRepository extends CommonRepository<Trip> {

    List<Trip> getAllFrom(String city);

    List<Trip> getAllTo(String city);

}
