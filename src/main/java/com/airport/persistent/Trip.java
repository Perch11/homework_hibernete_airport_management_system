package com.airport.persistent;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "trip",
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "company_id", "airplane", "town_from", "town_to", "time_out", "time_in"})})
public class Trip {

    @Id
    @Column(updatable = false)
    private int tripNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false,updatable = false)
    private Company company;

    @Column(nullable = false, length = 24)
    private String airplane;

    @Column(name = "town_from", nullable = false, length = 24)
    private String townFrom;

    @Column(name = "town_to", nullable = false, length = 24)
    private String townTo;

    @Column(name = "time_out", nullable = false)
    private Timestamp timeOut;

    @Column(name = "time_in", nullable = false)
    private Timestamp timeIn;


    public Trip() {
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(final int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(final String airplane) {
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(final String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(final String townTo) {
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(final Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(final Timestamp timeIn) {
        this.timeIn = timeIn;
    }
}