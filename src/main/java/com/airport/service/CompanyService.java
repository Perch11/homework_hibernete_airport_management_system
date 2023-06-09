package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerCompany;
import com.airport.convert_classes.per_to_mod.PerToModCompany;
import com.airport.hibernate.HibernateUtil;
import com.airport.model.Company;
import com.airport.repository.CompanyRepository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.airport.validator.Validator.*;

public class CompanyService implements CompanyRepository {

    private static final ModToPerCompany MOD_TO_PER = new ModToPerCompany();
    private static final PerToModCompany PER_TO_MOD = new PerToModCompany();


    @Override
    public Company getBy(int id) {
        checkId(id);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            com.airport.persistent.Company company = session.get(com.airport.persistent.Company.class, id);
            if (company == null) {
                System.out.println("There is no company with " + id + " id: ");
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
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.Company> query = session.createQuery("FROM Company ", com.airport.persistent.Company.class);
            List<com.airport.persistent.Company> companieslist = query.getResultList();
            if (companieslist.isEmpty()) {
                System.out.println("There is no company:");
                transaction.rollback();
                return null;
            }
            Set<Company> companies = new LinkedHashSet<>(companieslist.size());
            for (com.airport.persistent.Company item : companieslist) {
                Company company = PER_TO_MOD.getModelFromPersistent(item);
                companies.add(company);
            }
            transaction.commit();
            return companies;

        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        checkParamGetMethodCompany(offset, perPage, sort);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<List<com.airport.persistent.Company>> companies = session.createQuery("FROM Company order by " + sort);
            companies.setFirstResult(offset);
            companies.setMaxResults(perPage);

            if (companies.getResultList().isEmpty()) {
                System.out.println("There is no company:");
                transaction.rollback();
                return null;
            }
            Set<Company> companySet = new LinkedHashSet<>(companies.getResultList().size());

            for (int i = 0; i < companies.getResultList().size(); i++) {
                com.airport.persistent.Company tempCompany = (com.airport.persistent.Company) companies.getResultList().get(i);
                companySet.add(PER_TO_MOD.getModelFromPersistent(tempCompany));
            }
            transaction.commit();
            return companySet.isEmpty() ? null : companySet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company save(Company item) {
        checkNull(item);
        if (exists(item)) {
            System.out.println("[" + item + "] company already exists: ");
            return null;
        }
        if (doesExistWith(item.getName())) {
            System.out.println("Company with " + item.getName() + " already exists: ");
            return null;
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            com.airport.persistent.Company company = MOD_TO_PER.getPersistentFromModel(item);

            session.save(company);
            item.setId(company.getId());

            transaction.commit();
            return item;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBy(int id, String newName, Date newFoundDate) {
        checkId(id);
        if (getBy(id) == null) {
            System.out.println("Company with " + id + " id not found: ");
            return false;
        }
        if (doesExistWith(newName)) {
            System.out.println("Company with " + newName + " already exists: ");
            return false;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            com.airport.persistent.Company company = session.get(com.airport.persistent.Company.class, id);
            if (company == null) {
                System.out.println("Company with " + id + " id not found: ");
                transaction.rollback();
                return false;
            }
            if (!validateStringIsEmptyOrNull(newName)) {
                company.setName(newName);
            }
            if (!validateObjectNull(newFoundDate)) {
                company.setFoundDate(newFoundDate);
            }
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteBy(int id) {
        checkId(id);
        if (getBy(id) == null) {
            System.out.println("Company with " + id + " id not found: ");
            return false;
        }
        if (existsTripBy(id)) {
            System.out.println("First remove company by " + id + " in trip table: ");
            return false;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            com.airport.persistent.Company company = session.get(com.airport.persistent.Company.class, id);

            if (company == null) {
                transaction.rollback();
                return false;
            }
            session.delete(company);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean exists(com.airport.model.Company company) {
        checkNull(company);

        for (Company item : getAll()) {
            if (company.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getId(com.airport.model.Company company) {
        checkNull(company);

        for (Company item : getAll()) {
            if (company.equals(item)) {
                return item.getId();
            }
        }
        return -1;
    }

    @Override
    public boolean existsTripBy(int companyId) {
        checkId(companyId);

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "select t from Trip as t where t.company = :companyId";
            TypedQuery<com.airport.persistent.Trip> tripTypedQuery = session.createQuery(hql);
            tripTypedQuery.setParameter("companyId", companyId);
            transaction.commit();

            return !tripTypedQuery.getResultList().isEmpty();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public boolean doesExistWith(String name) {
        validateString(name);

        for (Company tempCompany : getAll()) {
            if (tempCompany.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
