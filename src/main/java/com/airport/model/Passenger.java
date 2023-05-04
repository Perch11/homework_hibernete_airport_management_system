package com.airport.model;

import com.airport.validator.Validator;

import java.util.Objects;

public class Passenger {

    private int id;
    private String name;
    private String phone;
    private Address address;

    public Passenger(final int id,
                     final String name,
                     final String phone,
                     final Address address) {
        setId(id);
        setName(name);
        setPhone(phone);
        setAddress(address);
    }

    public Passenger(final String name,
                     final String phone,
                     final Address address) {
        setName(name);
        setPhone(phone);
        setAddress(address);
    }

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        Validator.checkId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        Validator.validateString(phone);
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        Validator.checkNull(address);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(name, passenger.name) && Objects.equals(phone, passenger.phone) && address.equals(passenger.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address);
    }
}