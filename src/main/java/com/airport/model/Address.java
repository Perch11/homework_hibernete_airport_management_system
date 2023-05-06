package com.airport.model;



import java.util.Objects;

import static com.airport.validator.Validator.*;

public class Address {

    private int id;
    private String country;
    private String city;

    public Address(final String country, final String city) {
        setCountry(country);
        setCity(city);
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        checkId(id);
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        validateString(country);
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        validateString(city);
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return Objects.equals(country, that.country) && Objects.equals(city, that.city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                "}\n";
    }

}