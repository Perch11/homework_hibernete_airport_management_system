package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerCompany;
import com.airport.convert_classes.mod_to_per.ModToPerTrip;
import com.airport.convert_classes.per_to_mod.PerToModTrip;
import com.airport.hibernate.HibernateUtil;
import com.airport.model.Company;
import com.airport.model.Trip;
import com.airport.persistent.Address;
import com.airport.repository.TripRepository;
import com.airport.validator.Validator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TripService implements TripRepository {
    Session session;
    private static final PerToModTrip PER_TO_MOD = new PerToModTrip();
    private static final ModToPerTrip MOD_TO_PER = new ModToPerTrip();
    private static final ModToPerCompany MOD_TO_PER_COMPANY = new ModToPerCompany();
    private static final CompanyService COMPANY_SERVICE = new CompanyService();

    @Override
    public Set<Trip> getAllFrom(String city) {
        Validator.validateString(city);
        Set<Trip> trips = new LinkedHashSet<>();
        for (Trip trip : getAll()) {
            if (trip.getTownFrom().equals(city)) {
                trips.add(trip);
            }
        }
        return trips;
    }

    @Override
    public Set<Trip> getAllTo(String city) {
        Validator.validateString(city);
        Set<Trip> trips = new LinkedHashSet<>();
        for (Trip trip : getAll()) {
            if (trip.getTownTo().equals(city)) {
                trips.add(trip);
            }
        }
        return trips;
    }

    @Override
    public Trip getBy(int id) {
        Validator.checkId(id);
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            com.airport.persistent.Trip trip = session.get(com.airport.persistent.Trip.class, id);
            if (trip == null) {
                transaction.rollback();
                return null;
            }
            transaction.commit();
            return PER_TO_MOD.getModelFromPersistent(trip);
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Set<Trip> getAll() {
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.Trip> query = session.createQuery("FROM Trip ", com.airport.persistent.Trip.class);

            List<com.airport.persistent.Trip> listpersistent = query.getResultList();
            if (listpersistent.isEmpty()) {
                transaction.rollback();
                return null;
            }
            Set<com.airport.model.Trip> listmodel = new LinkedHashSet<>(listpersistent.size());
            for (com.airport.persistent.Trip item : listpersistent) {
                com.airport.model.Trip trip = PER_TO_MOD.getModelFromPersistent(item);

                listmodel.add(trip);
            }
            transaction.commit();
            return listmodel;

        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
//        //Validator.checkParamGetMethodCompany(offset, perPage, sort);
//
//        Transaction transaction = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            TypedQuery<List<com.airport.persistent.Company>> companies = session.createQuery("FROM Company order by " + sort);
//            companies.setFirstResult(offset);
//            companies.setMaxResults(perPage);
//
//            if (companies.getResultList().isEmpty()) {
//                transaction.commit();
//                return null;
//            }
//            Set<Company> companySet = new LinkedHashSet<>(companies.getResultList().size());
//
//            for (int i = 0; i < companies.getResultList().size(); i++) {
//                com.airport.persistent.Company tempCompany = (com.airport.persistent.Company) companies.getResultList().get(i);
//                companySet.add(PER_TO_MOD.getModelFromPersistent(tempCompany));
//            }
//            transaction.commit();
//            return companySet.isEmpty() ? null : companySet;
//        } catch (HibernateException e) {
//            assert transaction != null;
//            transaction.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            session.close();
//        }
        return null;
    }

    /**
     * @param item
     * @return
     */
    //TODO
    @Override
    public Trip save(Trip item) {
//        Validator.checkNull(item);
//        int companyId = COMPANY_SERVICE.getId(item.getCompany());
//
//
//        Transaction transaction = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            com.airport.persistent.Trip trip = MOD_TO_PER.getPersistentFromModel(item);
//
//            if (companyId == -1) {
//
//            }
//
//
//        } catch (HibernateException e) {
//            assert transaction != null;
//            transaction.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            session.close();
//        }
        return null;
    }


    @Override
    public boolean updateBy(int id, String airplane, String townFrom,
                            String townTo,
                            Timestamp timeOut,
                            Timestamp timeIn) {
        Validator.checkId(id);

        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            com.airport.persistent.Trip trip = session.get(com.airport.persistent.Trip.class, id);
            if (trip == null){
                transaction.rollback();
                return false;
            }
            if(!(airplane == null || airplane.isEmpty()) ){
                trip.setAirplane(airplane);
            }if(!(townFrom == null || townFrom.isEmpty()) ){
                trip.setTownFrom(townFrom);
            }if(!(townTo == null || townTo.isEmpty()) ){
                trip.setTownTo(townTo);
            }if(!(timeOut == null) ){
                trip.setTimeOut(timeOut);
            }if(!(timeIn == null) ){
                trip.setTimeIn(timeIn);
            }

            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteBy(int id) {
        Validator.checkId(id);
        if (existsPassInTripBy(id)) {
            System.out.println("First remove PassInTrip by " + id + " in PassInTrip table: ");
            return false;
        }
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            com.airport.persistent.Trip trip = session.get(com.airport.persistent.Trip.class, id);

            if (trip == null) {
                transaction.rollback();
                return false;
            }
            session.delete(trip);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    private boolean existsPassInTripBy(int tripId) {
        Validator.checkId(tripId);

        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "select p from PassInTrip as p where p.trip = :tripId";
            TypedQuery<com.airport.persistent.PassInTrip> passInTripTypedQuery = session.createQuery(hql);
            passInTripTypedQuery.setParameter("tripId", tripId);
            transaction.commit();

            return !passInTripTypedQuery.getResultList().isEmpty();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
