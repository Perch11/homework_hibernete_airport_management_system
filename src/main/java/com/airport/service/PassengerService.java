package com.airport.service;

import com.airport.convert_classes.per_to_mod.PerToModPassenger;
import com.airport.hibernate.HibernateUtil;
import com.airport.model.PassInTrip;
import com.airport.model.Passenger;
import com.airport.persistent.Address;
import com.airport.persistent.Trip;
import com.airport.repository.PassengerRepository;
import com.airport.validator.Validator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.airport.validator.Validator.*;

public class PassengerService implements PassengerRepository {

    private static final PerToModPassenger PER_TO_MOD = new PerToModPassenger();
    private static final TripService TRIP_SERVICE = new TripService();
    private static final PassInTripService PASS_IN_TRIP_SERVICE = new PassInTripService();
    private static final AddressService ADDRESS_SERVICE = new AddressService();


    @Override
    public List<Passenger> getAllOf(int tripId) {
        checkId(tripId);
        if (TRIP_SERVICE.getBy(tripId) == null) {
            System.out.println("invalid tripId");
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Passenger> passengers = new LinkedList<>();

            for (PassInTrip passInTrip : PASS_IN_TRIP_SERVICE.getAll()) {
                if (passInTrip.getTrip().getTripNumber() == tripId) {
                    passengers.add(passInTrip.getPassenger());
                }
            }
            return passengers;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean registerTrip(int id, int tripId) {
        return false;
    }

    @Override
    public boolean cancelTrip(int id, int tripId) {
        return false;
    }

    @Override
    public Passenger getBy(int id) {
        checkId(id);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            com.airport.persistent.Passenger passenger = session.get(com.airport.persistent.Passenger.class, id);
            if (passenger == null) {
                transaction.rollback();
                return null;
            }
            transaction.commit();
            return PER_TO_MOD.getModelFromPersistent(passenger);
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Passenger> getAll() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.Passenger> passengerTypedQuery = session.createQuery("FROM Passenger ", com.airport.persistent.Passenger.class);

            List<com.airport.persistent.Passenger> passengers = passengerTypedQuery.getResultList();
            if (passengers.isEmpty()) {
                transaction.rollback();
                return null;
            }
            Set<com.airport.model.Passenger> passengerSet = new LinkedHashSet<>(passengers.size());
            for (com.airport.persistent.Passenger item : passengers) {
                com.airport.model.Passenger passenger = PER_TO_MOD.getModelFromPersistent(item);
                passengerSet.add(passenger);
            }
            transaction.commit();
            return passengerSet;

        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        Validator.checkParamGetMethodPassenger(offset, perPage, sort);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            TypedQuery<com.airport.persistent.Passenger> query = session.createQuery("FROM Passenger order by " + sort);
            query.setFirstResult(offset);
            query.setMaxResults(perPage);

            if (query.getResultList().isEmpty()) {
                transaction.rollback();
                return null;
            }
            Set<com.airport.model.Passenger> passengerSet = new LinkedHashSet<>(query.getResultList().size());

            for (int i = 0; i < query.getResultList().size(); i++) {
                com.airport.persistent.Passenger tempPassenger = query.getResultList().get(i);
                passengerSet.add(PER_TO_MOD.getModelFromPersistent(tempPassenger));
            }
            transaction.commit();
            return passengerSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passenger save(Passenger item) {
        checkNull(item);

        int idOfAddress = ADDRESS_SERVICE.getId(item.getAddress());

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())  {
            transaction = session.beginTransaction();

            com.airport.persistent.Passenger passenger = new com.airport.persistent.Passenger();
            passenger.setName(item.getName());
            passenger.setPhone(item.getPhone());

            if (idOfAddress > 0) {
                passenger.setAddress(session.get(Address.class, idOfAddress));
            } else {
                Address newAddress = new Address();
                newAddress.setCountry(item.getAddress().getCountry());
                newAddress.setCity(item.getAddress().getCity());

                session.save(newAddress);
                passenger.setAddress(newAddress);
            }

            session.save(passenger);
            item.setId(passenger.getId());

            transaction.commit();
            return item;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBy(int id, Passenger item) {
        return false;
    }

    @Override
    public boolean deleteBy(int id) {
        return false;
    }
}
