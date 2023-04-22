package com.airport.repository;

import com.airport.model.Passenger;
import com.airport.repository.common.CommonRepository;

import java.util.List;

public interface PassengerRepository extends CommonRepository<Passenger> {

    List<Passenger> getAllOf(int tripId);

    boolean registerTrip(int id, int tripId);

    boolean cancelTrip(int id, int tripId);

}
