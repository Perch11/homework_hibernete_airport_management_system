package com.airport.service;

import com.airport.model.Passenger;
import com.airport.repository.PassengerRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public class PassengerService implements PassengerRepository {

    private Session session;


    @Override
    public List<Passenger> getAllOf(int tripId) {
        return null;
    }

    @Override
    public boolean registerTrip(int id, int tripId) {
        return false;
    }

    @Override
    public boolean cancelTrip(int id, int tripId) {
        return false;
    }

    @Override
    public Passenger getBy(int id) {
        return null;
    }

    @Override
    public Set<Passenger> getAll() {
        return null;
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public Passenger save(Passenger item) {
        return null;
    }

    @Override
    public boolean updateBy(int id, Passenger item) {
        return false;
    }

    @Override
    public boolean deleteBy(int id) {
        return false;
    }
}
