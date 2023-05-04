package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerCompany;
import com.airport.convert_classes.mod_to_per.ModToPerTrip;
import com.airport.convert_classes.per_to_mod.PerToModTrip;
import com.airport.hibernate.HibernateUtil;
import com.airport.model.Trip;
import com.airport.persistent.Company;
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

import static com.airport.validator.Validator.*;

public class TripService implements TripRepository {


    private static final PerToModTrip PER_TO_MOD = new PerToModTrip();

    private static final CompanyService COMPANY_SERVICE = new CompanyService();

    @Override
    public Set<Trip> getAllFrom(String city) {
        validateString(city);
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
        validateString(city);
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

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        }
    }

    @Override
    public Set<Trip> getAll() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.Trip> tripTypedQuery = session.createQuery("FROM Trip ", com.airport.persistent.Trip.class);

            List<com.airport.persistent.Trip> listpersistent = tripTypedQuery.getResultList();
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
        }
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        Validator.checkParamGetMethodTrip(offset, perPage, sort);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            TypedQuery<com.airport.persistent.Trip> query = session.createQuery("FROM Trip order by " + sort);
            query.setFirstResult(offset);
            query.setMaxResults(perPage);

            if (query.getResultList().isEmpty()) {
                transaction.rollback();
                return null;
            }
            Set<com.airport.model.Trip> tripSet = new LinkedHashSet<>(query.getResultList().size());

            for (int i = 0; i < query.getResultList().size(); i++) {
                com.airport.persistent.Trip tempTrip = query.getResultList().get(i);
                tripSet.add(PER_TO_MOD.getModelFromPersistent(tempTrip));
            }
            transaction.commit();
            return tripSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists(com.airport.model.Trip trip) {
        checkNull(trip);

        for (Trip item : getAll()) {
            if (trip.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getId(com.airport.model.Trip trip) {
        checkNull(trip);

        for (com.airport.model.Trip item : getAll()) {
            if (trip.equals(item)) {
                return item.getTripNumber();
            }
        }
        return -1;
    }

    @Override
    public Trip save(Trip item) {
        checkNull(item);
        if (exists(item)) {
            System.out.println("[" + item + "] address already exists: ");
            return null;
        }
        int companyId = COMPANY_SERVICE.getId(item.getCompany());
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            com.airport.persistent.Trip trip = new com.airport.persistent.Trip();
            trip.setTripNumber(item.getTripNumber());
            trip.setTimeIn(item.getTimeIn());
            trip.setTimeOut(item.getTimeOut());
            trip.setAirplane(item.getAirplane());
            trip.setTownFrom(item.getTownFrom());
            trip.setTownTo(item.getTownTo());
            if (companyId > 0) {
                trip.setCompany(session.get(Company.class, companyId));
            } else {
                Company newCompany = new Company();
                newCompany.setName(item.getCompany().getName());
                newCompany.setFoundDate(item.getCompany().getFoundDate());

                session.save(newCompany);
                trip.setCompany(newCompany);
            }
            session.save(trip);
            item.setTripNumber(trip.getTripNumber());
            transaction.commit();
            return item;

        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean updateBy(int id, String airplane, String townFrom, String townTo, Timestamp timeOut, Timestamp timeIn) {
        Validator.checkId(id);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            com.airport.persistent.Trip trip = session.get(com.airport.persistent.Trip.class, id);
            if (validateObjectNull(trip)) {
                transaction.rollback();
                return false;
            }
            if (!validateStringIsEmptyOrNull(airplane)) {
                trip.setAirplane(airplane);
            }
            if (!validateStringIsEmptyOrNull(townFrom)) {
                trip.setTownFrom(townFrom);
            }
            if (!validateStringIsEmptyOrNull(townTo)) {
                trip.setTownTo(townTo);
            }
            if (!validateObjectNull(timeOut)) {
                trip.setTimeOut(timeOut);
            }
            if (!validateObjectNull(timeIn)) {
                trip.setTimeIn(timeIn);
            }
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteBy(int id) {
        checkId(id);
        if (existsPassInTripBy(id)) {
            System.out.println("First remove PassInTrip by " + id + " in PassInTrip table: ");
            return false;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        }
    }

    private boolean existsPassInTripBy(int tripId) {
        Validator.checkId(tripId);

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        }
    }
}
