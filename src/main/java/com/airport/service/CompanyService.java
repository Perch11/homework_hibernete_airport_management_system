package com.airport.service;

import com.airport.model.Company;
import com.airport.repository.CompanyRepository;
import org.hibernate.Session;

import java.util.Set;

public class CompanyService implements CompanyRepository {

    private Session session;


    @Override
    public Company getBy(int id) {
        return null;
    }

    @Override
    public Set<Company> getAll() {
        return null;
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public Company save(Company item) {
        return null;
    }

    @Override
    public boolean updateBy(int id, Company item) {
        return false;
    }

    @Override
    public boolean deleteBy(int id) {
        return false;
    }


    public void setSession(Session session) {
        if (session == null){
            throw new NullPointerException();
        }
        this.session = session;
    }

    private void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be a positive number: ");
        }
    }
}