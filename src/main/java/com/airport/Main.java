package com.airport;

import com.airport.service.PassengerService;


public class Main {
    public static void main(String[] args) {

//        PassInTripService passInTripService = new PassInTripService();
//        System.out.println(passInTripService.getAll());
        PassengerService passengerService = new PassengerService();
        System.out.println(passengerService.getAllOf(7771));
    }
}