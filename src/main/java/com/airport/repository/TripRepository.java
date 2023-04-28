package com.airport.repository;

import com.airport.model.Trip;
import com.airport.repository.common.CommonRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface TripRepository extends CommonRepository<Trip> {
    public boolean updateBy(int id, String airplane, String townFrom,
                            String townTo,
                            Timestamp timeOut,
                            Timestamp timeIn);

    Set<Trip> getAllFrom(String city);

    Set<Trip> getAllTo(String city);

}
