package com.airport.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.airport.validator.Validator.*;

public class PassInTrip {

    private int id;
    private Trip trip;
    private Passenger passenger;
    private Timestamp time;
    private String place;


    public PassInTrip(
            final Trip trip,
            final Passenger passenger,
            final Timestamp time,
            final String place
    ) {

        setTrip(trip);
        setPassenger(passenger);
        setPlace(place);
        setTime(time);
    }
    public PassInTrip(
            final Trip trip,
            final Passenger passenger,
            final String place
    ) {

        setTrip(trip);
        setPassenger(passenger);
        setPlace(place);
        this.time = Timestamp.valueOf(LocalDateTime.now());

    }

    public PassInTrip() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        checkId(id);
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(final Trip trip) {
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

    @Override
    public String toString() {
        return "PassInTrip{" +
                "id=" + id +
                ", trip=" + trip +
                ", passenger=" + passenger +
                ", time=" + time +
                ", place='" + place + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassInTrip that = (PassInTrip) o;
        return Objects.equals(trip, that.trip) &&
                Objects.equals(passenger, that.passenger) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trip, passenger, place);
    }
}
