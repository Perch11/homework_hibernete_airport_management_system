package com.airport.convert_classes.per_to_mod;

import com.airport.persistent.PassInTrip;

public class PerToModPassInTrip extends PerToMod<PassInTrip, com.airport.model.PassInTrip> {

    private static final PerToModTrip PER_TO_MOD_TRIP = new PerToModTrip();
    private static final PerToModPassenger PER_TO_MOD_PASSENGER = new PerToModPassenger();


    @Override
    public com.airport.model.PassInTrip getModelFromPersistent(PassInTrip persistent) {

        com.airport.model.PassInTrip model = new com.airport.model.PassInTrip();
        model.setId(persistent.getId());
        model.setPlace(persistent.getPlace());
        model.setTime(persistent.getTime());
        model.setTrip(PER_TO_MOD_TRIP.getModelFromPersistent(persistent.getTrip()));
        model.setPassenger(PER_TO_MOD_PASSENGER.getModelFromPersistent(persistent.getPassenger()));
        return model;
    }
}
