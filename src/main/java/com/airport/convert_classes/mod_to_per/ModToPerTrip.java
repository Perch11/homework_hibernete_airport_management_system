package com.airport.convert_classes.mod_to_per;

import com.airport.model.Trip;

import com.airport.validator.Validator;

public class ModToPerTrip extends ModToPer<Trip, com.airport.persistent.Trip> {
    private static final ModToPerCompany MOD_TO_PER = new ModToPerCompany();

    @Override
    public com.airport.persistent.Trip getPersistentFromModel(Trip model) {

        Validator.checkNull(model);
        com.airport.persistent.Trip persistent = new com.airport.persistent.Trip();
        persistent.setTripNumber(model.getTripNumber());
        persistent.setCompany(MOD_TO_PER.getPersistentFromModel(model.getCompany()));
        persistent.setAirplane(model.getAirplane());
        persistent.setTimeIn(model.getTimeIn());
        persistent.setTimeOut(model.getTimeOut());
        persistent.setTownTo(model.getTownTo());
        persistent.setTownFrom(model.getTownFrom());

        return persistent;
    }
}

