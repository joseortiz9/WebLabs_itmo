package ru.students.lab.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.students.lab.models.Point;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class PointDao {

    @PersistenceContext(name = "pointsManagement")
    protected EntityManager entityManager;

    public void add(Point entity) {
        entityManager.persist(entity);
    }

    public List<Point> getAll() {
        return entityManager.createQuery("from Point", Point.class).getResultList();
    }
}
