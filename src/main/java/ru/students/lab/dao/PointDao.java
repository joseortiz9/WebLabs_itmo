package ru.students.lab.dao;

import ru.students.lab.models.Point;
import ru.students.lab.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PointDao {

    public void add(Point entity) {
        EntityManager en = JpaUtil.getEntityFactory().createEntityManager();
        try {
            en.getTransaction().begin();
            en.persist(entity);
            en.getTransaction().commit();
        } finally {
            en.close();
        }
    }

    public List<Point> getAll() {
        EntityManager en = JpaUtil.getEntityFactory().createEntityManager();
        try {
            return en.createQuery("from Point", Point.class).getResultList();
        } finally {
            en.close();
        }
    }
}
