package com.airport.convert_classes.per_to_mod;

import com.airport.persistent.Company;
import com.airport.validator.Validator;

public class PerToModCompany extends PerToMod<Company, com.airport.model.Company>{

    @Override
    public com.airport.model.Company getModelFromPersistent(Company persistent) {
        Validator.checkNull(persistent);
        com.airport.model.Company model = new com.airport.model.Company();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setFoundDate(persistent.getFoundDate());
        return model;
    }


}
