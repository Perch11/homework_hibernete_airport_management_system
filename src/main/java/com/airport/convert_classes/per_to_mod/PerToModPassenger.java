package com.airport.convert_classes.per_to_mod;

import com.airport.persistent.Passenger;
import com.airport.validator.Validator;

public class PerToModPassenger extends PerToMod<Passenger, com.airport.model.Passenger> {

    private static final PerToModAddress PER_TO_MOD = new PerToModAddress();

    @Override
    public com.airport.model.Passenger getModelFromPersistent(Passenger persistent) {
        Validator.checkNull(persistent);
        com.airport.model.Passenger model = new com.airport.model.Passenger();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setPhone(persistent.getPhone());
        model.setAddress(PER_TO_MOD.getModelFromPersistent(persistent.getAddress()));
        return model;
    }
}
