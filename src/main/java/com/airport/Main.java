package com.airport;

import com.airport.hibernate.HibernateUtil;
import com.airport.insert_into_db.Inserter;
import com.airport.model.Address;
import com.airport.model.Company;
import com.airport.service.AddressService;
import com.airport.service.CompanyService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Inserter inserter = new Inserter();
//        inserter.setSession(session);
//        inserter.insertAddressTable();
//        inserter.insertCompanyTable();
//        inserter.insertPassengerTable();
//        inserter.insertTripTable();
//        inserter.insertPassInTripTable();

        AddressService addressService = new AddressService();
        addressService.setSession(session);
//        Address address = addressService.getBy(5);
//        System.out.println(address);
//        System.out.println(addressService.getAll());
//          Address address1 = new Address("Hayastan","Yerevan");
//        System.out.println(addressService.save(address1));
//        System.out.println(addressService.deleteBy(108));
//        System.out.println(addressService.getAll());
//        addressService.updateBy(2,new Address("Armenia","Gyumri"));
//        System.out.println(addressService.getAll());
//
//
//           System.out.println(addressService.deleteBy(100));
//        System.out.println(addressService.getAll());
//
//        System.out.println(addressService.get(4, 7, "country"));
        CompanyService companyService = new CompanyService();

        HibernateUtil.shutdown();
    }
}