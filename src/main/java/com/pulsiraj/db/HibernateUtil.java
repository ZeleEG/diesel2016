/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Dejan
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        SessionFactory factory = null;
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            factory = cfg.buildSessionFactory();
            /*
            Configuration configuration = new Configuration().configure();
	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
	                .applySettings(configuration.getProperties());
	        factory = configuration
	                .buildSessionFactory(builder.build());
             */
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return factory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
