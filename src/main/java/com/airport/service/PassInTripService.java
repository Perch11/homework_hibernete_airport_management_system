package com.airport.service;

import com.airport.convert_classes.per_to_mod.PerToModPassInTrip;
import com.airport.hibernate.HibernateUtil;
import com.airport.repository.PassInTripRepository;
import com.airport.validator.Validator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PassInTripService implements PassInTripRepository {

    private static final PerToModPassInTrip PER_TO_MOD_PASS_IN_TRIP = new PerToModPassInTrip();

    @Override
    public com.airport.model.PassInTrip getBy(int id) {
        Validator.checkId(id);
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
    @Override
    public Set<com.airport.model.PassInTrip> getAll() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.PassInTrip> query = session.createQuery("FROM PassInTrip ", com.airport.persistent.PassInTrip.class);
            List<com.airport.persistent.PassInTrip> list = query.getResultList();

            if (list.isEmpty()) {
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
    @Override
    public Set<com.airport.model.PassInTrip> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public com.airport.model.PassInTrip save(com.airport.model.PassInTrip item) {
        return null;
    }

    @Override
    public boolean deleteBy(int id) {
        return false;
    }

}
