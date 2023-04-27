package com.airport.model;

import com.airport.validator.Validator;

public class Address {

    private int id;
    private String country;
    private String city;

    public Address(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

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
        Validator.checkId(id);
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        Validator.validateString(country);
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        Validator.validateString(city);
        this.city = city;
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