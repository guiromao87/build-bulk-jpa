package br.com.cheeper;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestPostVlad {

    public static void main(String[] args) {
        Long inicio = System.currentTimeMillis();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cheeper");

        int entityCount = 1000;
        int batchSize = 25;

        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        for (int i = 0; i < entityCount; i++) {
                if (i > 0 && i % batchSize == 0) {
                    entityTransaction.commit();
                    entityTransaction.begin();

                    entityManager.clear();
                }
            Post post = new Post(String.format("Post %d", 2 + 1));
            entityManager.persist(post);
        }

        entityTransaction.commit();

        Long fim = System.currentTimeMillis();
        System.out.println(" ******* TEMPO: " + (fim - inicio) /1000);

        Session session = entityManager.unwrap(Session.class);

        Statistics statistics = session.getSessionFactory().getStatistics();

        System.out.println("statements: " + statistics.getPrepareStatementCount());
    }
}
