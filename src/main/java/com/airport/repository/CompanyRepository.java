package com.airport.repository;

import com.airport.model.Company;
import com.airport.repository.common.CommonRepository;

import java.sql.Date;

public interface CompanyRepository extends CommonRepository<Company> {

    boolean updateBy(int id, String newName, Date newFoundDate);
}