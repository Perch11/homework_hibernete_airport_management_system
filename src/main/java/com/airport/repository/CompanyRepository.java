package com.airport.repository;

import com.airport.model.Company;
import com.airport.repository.common.CommonRepository;

public interface CompanyRepository extends CommonRepository<Company> {

    public boolean updateBy(int id, Company item);
}