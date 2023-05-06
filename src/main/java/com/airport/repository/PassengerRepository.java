package com.airport.repository;

import com.airport.model.Address;
import com.airport.model.Passenger;
import com.airport.model.Trip;
import com.airport.repository.common.CommonRepository;

import java.util.List;

public interface PassengerRepository extends CommonRepository<Passenger> {

    boolean updateBy(int id, String newName, String newPhone, Address newAddress);
    List<Passenger> getAllOf(int tripId);

    public boolean cancelTrip(int tripId, int passengerId, String place);

    public boolean registerTrip(int tripId, Passenger passenger, String place);

}
