package com.airport.model;

import com.airport.validator.Validator;

import java.sql.Timestamp;

public class Trip {

    private int tripNumber;
    private Company company;
    private String airplane;
    private String townFrom;
    private String townTo;
    private Timestamp timeOut;
    private Timestamp timeIn;

    public Trip(final int tripNumber,
                final Company company,
                final String airplane,
                final String townFrom,
                final String townTo,
                final Timestamp timeOut,
                final Timestamp timeIn) {
        setTripNumber(tripNumber);
        setCompany(company);
        setAirplane(airplane);
        setTownFrom(townFrom);
        setTownTo(townTo);
        setTimeOut(timeOut);
        setTimeIn(timeIn);
    }
    public Trip(
                final String airplane,
                final String townFrom,
                final String townTo,
                final Timestamp timeOut,
                final Timestamp timeIn) {
        setAirplane(airplane);
        setTownFrom(townFrom);
        setTownTo(townTo);
        setTimeOut(timeOut);
        setTimeIn(timeIn);
    }

    public Trip() {
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(final int tripNumber) {
        Validator.checkId(tripNumber);
        this.tripNumber = tripNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        Validator.checkNull(company);
        this.company = company;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(final String airplane) {
        Validator.validateString(airplane);
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(final String townFrom) {
        Validator.validateString(townFrom);
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(final String townTo) {
        Validator.validateString(townTo);
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(final Timestamp timeOut) {
        Validator.checkNull(timeOut);
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(final Timestamp timeIn) {
        Validator.checkNull(timeIn);
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
}