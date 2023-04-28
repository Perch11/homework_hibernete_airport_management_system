package com.airport.convert_classes.per_to_mod;

import com.airport.persistent.Trip;
import com.airport.validator.Validator;

import java.util.Collection;
import java.util.LinkedHashSet;

public class PerToModTrip extends PerToMod<Trip, com.airport.model.Trip> {

    private static final PerToModCompany PER_TO_MOD_COMPANY = new PerToModCompany();

    @Override
    public com.airport.model.Trip getModelFromPersistent(Trip persistent) {
        Validator.checkNull(persistent);
        com.airport.model.Trip model = new com.airport.model.Trip();
        model.setTripNumber(persistent.getTripNumber());
        model.setAirplane(persistent.getAirplane());
        model.setTownTo(persistent.getTownTo());
        model.setTimeIn(persistent.getTimeIn());
        model.setTimeOut(persistent.getTimeOut());
        model.setTownFrom(persistent.getTownFrom());
        model.setCompany(PER_TO_MOD_COMPANY.getModelFromPersistent(persistent.getCompany()));
        return model;

    }
//    public Collection<com.airport.model.Trip> getModelListFromPersistent(Collection<Trip> persistentList) {
//        Validator.checkNull(persistentList);
//
//        Collection<com.airport.model.Trip> tripModSet = new LinkedHashSet<>();
//        for (Trip tempTrip : persistentList) {
//            tripModSet.add(getModelFromPersistent(tempTrip));
//        }
//        return tripModSet;
//    }
}
