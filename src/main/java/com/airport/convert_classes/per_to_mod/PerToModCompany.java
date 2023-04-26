package com.airport.convert_classes.per_to_mod;

import com.airport.persistent.Company;

public class PerToModCompany extends PerToMod<Company, com.airport.model.Company>{

    @Override
    public com.airport.model.Company getModelFromPersistent(Company persistent) {
        checkNull(persistent);
        com.airport.model.Company model = new com.airport.model.Company();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setFoundDate(persistent.getFoundDate());
        return model;
    }

    private void checkNull(Object obj) {
        if (obj == null)
            throw new NullPointerException("Passed null value as 'persistent':");
    }
}
