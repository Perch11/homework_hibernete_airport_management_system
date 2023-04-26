package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerAddress;
import com.airport.convert_classes.mod_to_per.ModToPerCompany;
import com.airport.convert_classes.per_to_mod.PerToModAddress;
import com.airport.convert_classes.per_to_mod.PerToModCompany;
import com.airport.model.Company;
import com.airport.repository.CompanyRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class CompanyService implements CompanyRepository {

    private Session session;
    private static final ModToPerCompany MOD_TO_PER = new ModToPerCompany();
    private static final PerToModCompany PER_TO_MOD = new PerToModCompany();


    @Override
    public Company getBy(int id) {
        checkId(id);
        com.airport.persistent.Company company;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            company = session.get(com.airport.persistent.Company.class, id);
            if (company == null) {
                transaction.rollback();
                return null;
            }
            transaction.commit();
            return PER_TO_MOD.getModelFromPersistent(company);
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
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