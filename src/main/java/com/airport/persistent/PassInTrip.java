package com.airport.persistent;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "pass_in_trip")
public class PassInTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", nullable = false)
    private List<Passenger> passengers;

    @OneToMany
    @JoinColumn(name = "trip_id", referencedColumnName = "id", nullable = false)
    private List<Trip> trips;

    @Column(nullable = false, updatable = false)
    private Timestamp time;

    @Column(nullable = false, length = 3)
    private String place;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}