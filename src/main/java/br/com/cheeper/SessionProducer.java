package br.com.cheeper;

import org.hibernate.Session;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionProducer {

    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("cheeper");

    public static Session getSession() {
            return factory.createEntityManager().unwrap(Session.class);
    }
}
