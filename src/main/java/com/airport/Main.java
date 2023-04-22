package com.airport;

import com.airport.hibernate.HibernateUtil;
import com.airport.insert_into_db.Inserter;
import com.airport.model.Address;
import com.airport.service.AddressService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();


        HibernateUtil.shutdown();
    }
}