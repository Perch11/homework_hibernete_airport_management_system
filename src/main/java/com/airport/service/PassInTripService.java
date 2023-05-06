package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerPassInTrip;
import com.airport.convert_classes.per_to_mod.PerToModPassInTrip;
import com.airport.hibernate.HibernateUtil;
import com.airport.model.PassInTrip;

import com.airport.model.Passenger;
import com.airport.persistent.Trip;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.airport.validator.Validator.*;

public class PassInTripService {

    private static final PerToModPassInTrip PER_TO_MOD_PASS_IN_TRIP = new PerToModPassInTrip();
    private static final ModToPerPassInTrip MOD_TO_PER_PASS_IN_TRIP = new ModToPerPassInTrip();
    private static final TripService TRIP_SERVICE = new TripService();
    private static final PassengerService PASSENGER_SERVICE = new PassengerService();


    public com.airport.model.PassInTrip getBy(int id) {
        checkId(id);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            com.airport.persistent.PassInTrip passInTrip = session.get(com.airport.persistent.PassInTrip.class, id);
            if (passInTrip == null) {
                transaction.rollback();
                return null;
            }
            transaction.commit();
            return PER_TO_MOD_PASS_IN_TRIP.getModelFromPersistent(passInTrip);

        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public Set<com.airport.model.PassInTrip> getAll() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.PassInTrip> query = session.createQuery("FROM PassInTrip ", com.airport.persistent.PassInTrip.class);
            List<com.airport.persistent.PassInTrip> list = query.getResultList();

            if (list.isEmpty()) {
                System.out.println("There is no order:");
                transaction.rollback();
                return null;
            }
            Set<com.airport.model.PassInTrip> list1 = new LinkedHashSet<>(list.size());
            for (com.airport.persistent.PassInTrip item : list) {
                com.airport.model.PassInTrip passInTrip = PER_TO_MOD_PASS_IN_TRIP.getModelFromPersistent(item);
                list1.add(passInTrip);
            }
            transaction.commit();
            return list1;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public PassInTrip save(int tripId, Passenger passenger, String place) {


        if (validateObjectNull(TRIP_SERVICE.getBy(tripId))) {
            System.out.println("Trip with " + tripId + " does not exist: ");
            return null;
        }

        int passengerId = PASSENGER_SERVICE.getId(passenger);
        if (passengerId < 0) {
            passengerId = PASSENGER_SERVICE.save(passenger).getId();
        }

        if (validateStringIsEmptyOrNull(place)|| place.length() > 3) {
            throw new IllegalArgumentException("Invalid place: ");
        }

        PassInTrip passInTrip = new PassInTrip(TRIP_SERVICE.getBy(tripId), passenger, place);
        if (exists(passInTrip)) {
            System.out.println("Order already exists: ");
            return null;
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            com.airport.persistent.PassInTrip passInTripPer = MOD_TO_PER_PASS_IN_TRIP.getPersistentFromModel(passInTrip);
            passInTripPer.setTrip(session.get(Trip.class, tripId));
            passInTripPer.setPassenger(session.get(com.airport.persistent.Passenger.class, passengerId));

            session.save(passInTripPer);
            passInTrip.setId(passInTripPer.getId());

            transaction.commit();
            return passInTrip;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public PassInTrip delete(int tripId, int passengerId, String place) {
        checkId(tripId);
        checkId(passengerId);

        if (validateObjectNull(TRIP_SERVICE.getBy(tripId))) {
            System.out.println("Trip with " + tripId + " does not exist: ");
            return null;
        }

        if (validateObjectNull(PASSENGER_SERVICE.getBy(passengerId))) {
            System.out.println("Passenger with " + passengerId + " does not exist: ");
            return null;
        }

        if (validateStringIsEmptyOrNull(place)|| place.length() > 3) {
            throw new IllegalArgumentException("Invalid place: ");
        }

        PassInTrip passInTrip = new PassInTrip(TRIP_SERVICE.getBy(tripId), PASSENGER_SERVICE.getBy(passengerId), place);
        int id = getId(passInTrip);
        if (id < 0) {
            System.out.println("Order does not exist: ");
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(session.get(com.airport.persistent.PassInTrip.class, id));

            transaction.commit();
            return passInTrip;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
    public Set<PassInTrip> getAllByPassenger(int id) {
        checkId(id);

        return getAll()
                .stream()
                .filter(passInTrip -> id == passInTrip.getPassenger().getId())
                .collect(Collectors.toSet());
    }

    public Set<PassInTrip> getAllByTrip(int id) {
        checkId(id);

        return getAll()
                .stream()
                .filter(passInTrip -> id == passInTrip.getTrip().getTripNumber())
                .collect(Collectors.toSet());
    }

    public int getId(PassInTrip passInTripMod) {
        checkNull(passInTripMod);

        for (PassInTrip tempPassInTrip : getAll()) {
            if (passInTripMod.equals(tempPassInTrip)) {
                return tempPassInTrip.getId();
            }
        }
        return -1;
    }


    public boolean exists(PassInTrip passInTripMod) {
        checkNull(passInTripMod);

        for (PassInTrip tempPassInTrip : getAll()) {
            if (passInTripMod.equals(tempPassInTrip)) {
                return true;
            }
        }
        return false;
    }

}
