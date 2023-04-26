package com.airport.service;

import com.airport.convert_classes.mod_to_per.ModToPerAddress;
import com.airport.convert_classes.per_to_mod.PerToModAddress;
import com.airport.model.Address;
import com.airport.repository.AddressRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AddressService implements AddressRepository {

    private Session session;
    private static final ModToPerAddress MOD_TO_PER = new ModToPerAddress();
    private static final PerToModAddress PER_TO_MOD = new PerToModAddress();

    @Override
    public Address getBy(int id) {
        checkId(id);
        com.airport.persistent.Address address;
        Transaction transaction = null;

        try {
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
        }
    }

    @Override
    public Set<Address> getAll() {
        Transaction transaction = null;

        try {
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
        }
    }

    @Override
    public Set<Address> get(int offset, int perPage, String sort) {

        checkParamGetMethod(offset,perPage,sort);

        Transaction transaction = null;
        try {
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
        }
    }

    private void checkParamGetMethod(int offset,int perPage,String sort){
        if (offset <= 0 || perPage <= 0) {
            throw new IllegalArgumentException("Passed non-positive value as 'offset' or 'perPage': ");
        }
        if (sort == null || sort.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'sort': ");
        }
        if (!sort.equals("id") && !sort.equals("country") && !sort.equals("city")) {
            throw new IllegalArgumentException("Parameter 'sort' must be 'id' or 'country' or 'city': ");
        }
    }

    @Override
    public Address save(Address item) {
        checkNull(item);

        com.airport.persistent.Address address = MOD_TO_PER.getPersistentFromModel(item);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
//            com.airport.persistent.Address address = new com.airport.persistent.Address();
//            address.setCity(item.getCity());
//            address.setCountry(item.getCountry());
            session.save(address);
            item.setId(address.getId());

            transaction.commit();
            return item;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBy(int id, Address item) {
        checkId(id);
        checkNull(item);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            com.airport.persistent.Address address = session.get(com.airport.persistent.Address.class, id);

            if (address == null) {
                transaction.commit();
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
        }
    }

    @Override
    public boolean deleteBy(int id) {
        checkId(id);

        if (existsPassengerBy(id)) {
            System.out.println("First remove address by " + id + " in passenger table: ");
            return false;
        }

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            com.airport.persistent.Address address = session.get(com.airport.persistent.Address.class, id);

            if (address == null) {
                transaction.commit();
                return false;
            }

            session.delete(address);
            transaction.commit();
            return true;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public void setSession(Session session) {
        if (session == null) {
            throw new NullPointerException();
        }
        this.session = session;
    }

    private void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be a positive number: ");
        }
    }

    private void checkNull(Object item) {
        if (item == null) {
            throw new NullPointerException("Null Pointer Exception cavd tanem");
        }
    }

    private boolean existsPassengerBy(int addressId) {
        checkId(addressId);

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "select p from Passenger as p where p.address = :addressId";
            TypedQuery<com.airport.persistent.Passenger> passengerTypedQuery = session.createQuery(hql);
            transaction.commit();

            return passengerTypedQuery != null;
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}