package com.airport.convert_classes.mod_to_per;

import com.airport.model.Address;
import com.airport.validator.Validator;

public class ModToPerAddress extends ModToPer<Address, com.airport.persistent.Address> {
    @Override

    public com.airport.persistent.Address getPersistentFromModel(Address model) {
        Validator.checkNull(model);
        com.airport.persistent.Address persistent = new com.airport.persistent.Address();
        persistent.setCountry(model.getCountry());
        persistent.setCity(model.getCity());
        return persistent;
    }


}

