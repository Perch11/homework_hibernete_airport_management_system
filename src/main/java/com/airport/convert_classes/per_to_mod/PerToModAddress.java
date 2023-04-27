package com.airport.convert_classes.per_to_mod;

import com.airport.persistent.Address;
import com.airport.validator.Validator;

public class PerToModAddress extends PerToMod<Address, com.airport.model.Address> {

    @Override
    public com.airport.model.Address getModelFromPersistent(Address persistent) {
        Validator.checkNull(persistent);
        com.airport.model.Address model = new com.airport.model.Address();
        model.setId(persistent.getId());
        model.setCity(persistent.getCity());
        model.setCountry(persistent.getCountry());
        return model;
    }


}
