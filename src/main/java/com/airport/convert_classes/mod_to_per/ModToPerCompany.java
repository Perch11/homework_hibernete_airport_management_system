package com.airport.convert_classes.mod_to_per;

import com.airport.model.Company;
import com.airport.validator.Validator;

public class ModToPerCompany extends ModToPer<Company, com.airport.persistent.Company> {
    @Override
    public com.airport.persistent.Company getPersistentFromModel(Company model) {

        Validator.checkNull(model);
        com.airport.persistent.Company persistent = new com.airport.persistent.Company();
        persistent.setName(model.getName());
        persistent.setFoundDate(model.getFoundDate());
        return persistent;
    }

}
