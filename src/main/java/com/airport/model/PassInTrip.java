package com.airport.model;

import com.airport.persistent.Passenger;
import com.airport.persistent.Trip;
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

    public void setId( final int id) {
        if (id <= 0)
            throw new IllegalArgumentException("'passintrip' id must be positive number: ");
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip( final Trip trip) {
        checkNull(trip);
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(final Passenger passenger) {
        checkNull(passenger);
        this.passenger = passenger;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(final Timestamp time) {
        checkNull(time);
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        validateString(place);
        this.place = place;
    }

    private void checkNull(final Object obj) {
        if (obj == null) {
            throw new NullPointerException("Passed null value: ");
        }
    }


    private void validateString(final String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value: ");
        }
    }
}
