package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerAddress;
import com.airport.convert_classes.per_to_mod.PerToModAddress;
import com.airport.hibernate.HibernateUtil;
import com.airport.model.Address;
import com.airport.repository.AddressRepository;
import com.airport.validator.Validator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AddressService implements AddressRepository {

    Session session;
    private static final ModToPerAddress MOD_TO_PER = new ModToPerAddress();
    private static final PerToModAddress PER_TO_MOD = new PerToModAddress();

    @Override
    public Address getBy(int id) {
        Validator.checkId(id);
        com.airport.persistent.Address address;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            address = session.get(com.airport.persistent.Address.class, id);
            if (address == null) {
                transaction.rollback();
                return null;
            }
            transaction.commit();
            return PER_TO_MOD.getModelFromPersistent(address);
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public Set<Address> getAll() {
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            TypedQuery<com.airport.persistent.Address> query = session.createQuery("FROM Address", com.airport.persistent.Address.class);

            List<com.airport.persistent.Address> list = query.getResultList();
            if (list.isEmpty()) {
                transaction.rollback();
                return null;
            }
            Set<Address> list1 = new LinkedHashSet<>(list.size());
            for (com.airport.persistent.Address item : list) {
                Address address1 = PER_TO_MOD.getModelFromPersistent(item);

                list1.add(address1);
            }
            transaction.commit();
            return list1;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
    @Override
    public Set<Address> get(int offset, int perPage, String sort) {

        Validator.checkParamGetMethodAddress(offset,perPage,sort);

        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            TypedQuery<List<com.airport.persistent.Address>> addresses = session.createQuery("FROM Address order by " + sort);
            addresses.setFirstResult(offset);
            addresses.setMaxResults(perPage);


            if (addresses.getResultList().isEmpty()) {
                transaction.commit();
                return null;
            }
            Set<Address> addressSet = new LinkedHashSet<>(addresses.getResultList().size());

            for (int i = 0; i < addresses.getResultList().size(); i++) {
                com.airport.persistent.Address tempPerAddress = (com.airport.persistent.Address) addresses.getResultList().get(i);
                addressSet.add(PER_TO_MOD.getModelFromPersistent(tempPerAddress));
            }

            transaction.commit();
            return addressSet.isEmpty() ? null : addressSet;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public Address save(Address item) {
        Validator.checkNull(item);

        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            com.airport.persistent.Address address = MOD_TO_PER.getPersistentFromModel(item);

            session.save(address);
            item.setId(address.getId());

            transaction.commit();
            return item;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
    @Override
    public boolean updateBy(int id, Address item) {
        Validator.checkId(id);
        Validator.checkNull(item);
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            com.airport.persistent.Address address = session.get(com.airport.persistent.Address.class, id);

            if (address == null) {
                transaction.rollback();
                return false;
            }
            address.setCity(item.getCity());
            address.setCountry(item.getCountry());
            transaction.commit();
            return true;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
    @Override
    public boolean deleteBy(int id) {
        Validator.checkId(id);

        if (existsPassengerBy(id)) {
            System.out.println("First remove address by " + id + " in passenger table: ");
            return false;
        }
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            com.airport.persistent.Address address = session.get(com.airport.persistent.Address.class, id);

            if (address == null) {
                transaction.rollback();
                return false;
            }
            session.delete(address);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
    private boolean existsPassengerBy(int addressId) {
        Validator.checkId(addressId);

        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "select p from Passenger as p where p.address = :addressId";
            TypedQuery<com.airport.persistent.Passenger> passengerTypedQuery = session.createQuery(hql);
            transaction.commit();

            return passengerTypedQuery != null;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
}