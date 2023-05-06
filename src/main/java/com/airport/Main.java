package com.airport;

import com.airport.convert_classes.per_to_mod.PerToModAddress;
import com.airport.convert_classes.per_to_mod.PerToModPassenger;
import com.airport.insert_into_db.Inserter;
import com.airport.model.Company;
import com.airport.model.Passenger;
import com.airport.model.Trip;
import com.airport.persistent.Address;
import com.airport.service.AddressService;
import com.airport.service.CompanyService;
import com.airport.service.PassengerService;
import com.airport.service.TripService;

import java.sql.Date;
import java.sql.Timestamp;


public class Main {
    public static void main(String[] args) {
        PassengerService passengerService = new PassengerService();
        System.out.println(passengerService.getAllOf(1100));
    }
}