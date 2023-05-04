package com.airport.repository;

import com.airport.model.Passenger;
import com.airport.model.Trip;
import com.airport.repository.common.CommonRepository;

import java.util.List;

public interface PassengerRepository extends CommonRepository<Passenger> {

    boolean updateBy(int id, Passenger item);
    List<Passenger> getAllOf(int tripId);

    boolean registerTrip(Passenger passenger, Trip trip);

    boolean cancelTrip(int id, int tripId);

}
