package com.airport.convert_classes.mod_to_per;

import com.airport.model.Address;

public class ModToPerAddress extends ModToPer<Address, com.airport.persistent.Address> {
    @Override

    public com.airport.persistent.Address getPersistentFromModel(Address model) {
        checkNull(model);
        com.airport.persistent.Address persistent = new com.airport.persistent.Address();
        persistent.setCountry(model.getCountry());
        persistent.setCity(model.getCity());
        return persistent;
    }


    private void checkNull(Object obj) {
        if (obj == null)
            throw new NullPointerException("Passed null value as 'model':");
    }
}

