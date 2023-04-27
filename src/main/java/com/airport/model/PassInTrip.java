package com.airport.model;

import com.airport.validator.Validator;

import java.sql.Timestamp;

public class PassInTrip {

    private int id;
    private Trip trip;
    private Passenger passenger;
    private Timestamp time;
    private String place;

    public PassInTrip(final int id,
                      final Trip trip,
                      final Passenger passenger,
                      final Timestamp time,
                      final String place
    ) {
        this.id = id;
        this.trip = trip;
        this.passenger = passenger;
        this.time = time;
        this.place = place;
    }

    public PassInTrip(
            final Trip trip,
            final Passenger passenger,
            final Timestamp time,
            final String place
    ) {

        this.trip = trip;
        this.passenger = passenger;
        this.time = time;
        this.place = place;
    }

    public PassInTrip() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        Validator.checkId(id);
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(final Trip trip) {
        Validator.checkNull(trip);
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(final Passenger passenger) {
        Validator.checkNull(passenger);
        this.passenger = passenger;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(final Timestamp time) {
        Validator.checkNull(time);
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        Validator.validateString(place);
        this.place = place;
    }
}
