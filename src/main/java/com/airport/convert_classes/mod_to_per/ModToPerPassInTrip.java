package com.airport.convert_classes.mod_to_per;

import com.airport.model.PassInTrip;
import com.airport.validator.Validator;

public class ModToPerPassInTrip extends ModToPer<PassInTrip, com.airport.persistent.PassInTrip> {
    private static final ModToPerTrip MOD_TO_PER_TRIP = new ModToPerTrip();
    private static final ModToPerPassenger MOD_TO_PER_PASSENGER = new ModToPerPassenger();

    @Override
    public com.airport.persistent.PassInTrip getPersistentFromModel(PassInTrip model) {
        Validator.checkNull(model);
        com.airport.persistent.PassInTrip persistent = new com.airport.persistent.PassInTrip();
        persistent.setTrip(MOD_TO_PER_TRIP.getPersistentFromModel(model.getTrip()));
        return persistent;
    }
}
