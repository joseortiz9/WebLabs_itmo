package ru.students.lab.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("pointsManagement");
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
