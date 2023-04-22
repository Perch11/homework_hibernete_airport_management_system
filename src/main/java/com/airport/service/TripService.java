package com.airport.service;

import com.airport.model.Trip;
import com.airport.repository.TripRepository;

import java.util.List;
import java.util.Set;

public class TripService implements TripRepository {
    @Override
    public List<Trip> getAllFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getAllTo(String city) {
        return null;
    }

    @Override
    public Trip getBy(int id) {
        return null;
    }

    @Override
    public Set<Trip> getAll() {
        return null;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip item) {
        return null;
    }

    @Override
    public boolean updateBy(int id, Trip item) {
        return false;
    }

    @Override
    public boolean deleteBy(int id) {
        return false;
    }
}
