package com.airport.model;

import com.airport.validator.Validator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.airport.validator.Validator.*;

public class Trip {

    private int tripNumber;
    private Company company;
    private String airplane;
    private String townFrom;
    private String townTo;
    private Timestamp timeOut;
    private Timestamp timeIn;

    public Trip(final int tripNumber,
                final String airplane,
                final String townFrom,
                final String townTo,
                final Timestamp timeOut,
                final Timestamp timeIn) {
        setTripNumber(tripNumber);
        setAirplane(airplane);
        setTownFrom(townFrom);
        setTownTo(townTo);
        setTimeOut(timeOut);
        setTimeIn(timeIn);
    }

    public Trip(
                final int tripNumber,
                final String airplane,
                final String townFrom,
                final String townTo) {
        setTripNumber(tripNumber);
        setAirplane(airplane);
        setTownFrom(townFrom);
        setTownTo(townTo);
        timeIn = Timestamp.valueOf(LocalDateTime.now());
        timeOut = Timestamp.valueOf(LocalDateTime.now());
    }

    public Trip() {
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(final int tripNumber) {
        checkId(tripNumber);
        this.tripNumber = tripNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        checkNull(company);
        this.company = company;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(final String airplane) {
        validateString(airplane);
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(final String townFrom) {
        validateString(townFrom);
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(final String townTo) {
        validateString(townTo);
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(final Timestamp timeOut) {
        checkNull(timeOut);
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(final Timestamp timeIn) {
        checkNull(timeIn);
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber=" + tripNumber +
                ", company=" + company +
                ", airplane='" + airplane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(company, trip.company) &&
                Objects.equals(airplane, trip.airplane) &&
                Objects.equals(townFrom, trip.townFrom) &&
                Objects.equals(townTo, trip.townTo) &&
                Objects.equals(timeOut, trip.timeOut) &&
                Objects.equals(timeIn, trip.timeIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, airplane, townFrom, townTo, timeOut, timeIn);
    }
}