package com.airport.repository;

import com.airport.model.Address;
import com.airport.repository.common.CommonRepository;

public interface AddressRepository extends CommonRepository<Address> {
     boolean updateBy(int id, Address item);
}