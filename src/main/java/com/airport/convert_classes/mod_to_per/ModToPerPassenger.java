package com.airport.convert_classes.mod_to_per;

import com.airport.model.Passenger;
import com.airport.validator.Validator;

public class ModToPerPassenger extends ModToPer<Passenger, com.airport.persistent.Passenger>{
    private static final ModToPerAddress MOD_TO_PER = new ModToPerAddress();
    @Override
    public com.airport.persistent.Passenger getPersistentFromModel(Passenger model) {
        Validator.checkNull(model);
        com.airport.persistent.Passenger persistent = new com.airport.persistent.Passenger();
        persistent.setName(model.getName());
        persistent.setPhone(model.getPhone());
        persistent.setAddress(MOD_TO_PER.getPersistentFromModel(model.getAddress()));
        return persistent;
    }
}
